package me.wobblyyyy.authenticationm.src.handlers;

import me.wobblyyyy.authenticationm.src.AuthenticationM;
import me.wobblyyyy.authenticationm.src.data.Configuration;
import me.wobblyyyy.authenticationm.src.data.Messages;
import me.wobblyyyy.authenticationm.src.data.player.UserAccount;
import me.wobblyyyy.authenticationm.src.services.player.PlayerServiceManager;
import org.bukkit.entity.Player;

/**
 * A class which handles everything related to authentication.
 * <p>
 * We have everything from logging in to creating a new account and even
 * changing an already existing account's password. I know, it's simply
 * incredible, and you wish you were cool enough to do that.
 * </p>
 *
 * @author Colin Robertson
 */
public class AuthenticationHandler {
    /**
     * Should be run whenever the player logs in.
     * <p>
     * Whenever AuthenticationHandler#tryAuthentication() is run, we
     * check to see if the player already has an account and is NOT
     * logged in. If both of those are the case, we're going to run this
     * here lovely bit of code.
     * </p>
     *
     * @param account the account to test logging in with
     */
    public static void onPlayerLogIn(UserAccount account) {
        Player p = PlayerServiceManager.getPlayer(account);
        if (Configuration.welcomeOnJoin) {
            PlayerServiceManager.sendPlayerMessage(
                    account,
                    Messages.buildWelcome()
            );
        }
        // Put the player on lock-down (bugged?)
        PlayerServiceManager.lockdownPlayer(account, true);
        if (Configuration.kickPlayersWhoDontAuthenticate) {
            AuthenticationM.tasks.scheduleTask(
                    () -> {
                        if (!p.isOnline()) return;
                        if (!AuthenticationM.data.authenticated
                                .contains(account.username.getUsername())) {
                            PlayerServiceManager.kickPlayer(
                                    account,
                                    Messages.buildLoginFailed()
                            );
                        }
                    },
                    Configuration.kickDelay * 20 // 600 ticks = 30 seconds
            );
        }
        if (Configuration.enableAutoLogin) {
            if (PlayerServiceManager.authenticatePlayerWithHostname(account)) {
                PlayerServiceManager.lockdownPlayer(
                        account,
                        false
                );
                PlayerServiceManager.sendPlayerMessage(
                        account,
                        Messages.buildAutoLogin()
                );
                AuthenticationM.data
                        .authenticated.add(account.username.getUsername());
            }
        }
    }

    /**
     * Whenever the player disconnects, automatically de-authenticate them.
     * <p>
     * We do this to make sure a user can't log in and then have another
     * play log in under their account.
     * </p>
     *
     * @param account the account which was disconnected
     */
    public static void onPlayerDisconnect(UserAccount account) {
        AuthenticationM.data
                .authenticated.remove(account.username.getUsername());
    }

    /**
     * Code to be run whenever authentication actually ends up working.
     *
     * @param account the account which succeeded in logging in
     */
    public static void authenticationSucceeded(UserAccount account) {
        AuthenticationM.data.accounts.put(
                account.username.getUsername(),
                account
        );
        AuthenticationM.data.lockdown.remove(account.username.getUsername());
        PlayerServiceManager.sendPlayerMessage(
                account,
                Messages.buildLoginSucceeded()
        );
        AuthenticationM.data.authenticated.add(account.username.getUsername());
    }

    /**
     * Code to be run whenever authentication fails. Woo-hoo.
     *
     * @param account the account which failed to log in
     */
    public static void authenticationFailed(UserAccount account) {
        PlayerServiceManager.sendPlayerMessage(
                account,
                Messages.buildLoginFailed()
        );
    }

    /**
     * Code to be run when an account is being registered. Yay!
     *
     * @param account the account which was registered
     */
    public static void registerAccount(UserAccount account) {
        // make sure the password is the right length
        if (isPasswordCorrectLength(account)) return;
        PlayerServiceManager.sendPlayerMessage(
                account,
                Messages.buildAccountCreated()
        );
        authenticationSucceeded(account);
    }

    /**
     * code to be run when the user's password is changed. Also yay!
     *
     * @param account the account which password was changed.
     */
    public static void changedPassword(UserAccount account) {
        if (isPasswordCorrectLength(account)) return;
        System.out.println(account.password.getPassword());
        System.out.println(account.password.getPassword());
        AuthenticationM.data.accounts.put(
                account.username.getUsername(),
                account
        );
        PlayerServiceManager.sendPlayerMessage(
                account,
                Messages.buildPasswordChanged()
        );
    }

    /**
     * Check whether or not the password is actually the right length.
     * <p>
     * Might be issues when using whirlpool hashing? Make sure to try to
     * get around that somehow.
     * </p>
     *
     * @param account the account with a password to check the length of.
     * @return whether or not the password is the correct length
     */
    private static boolean isPasswordCorrectLength(UserAccount account) {
        int length = account.password.getPassword().length();
        if (length > Configuration.maxPasswordLength) {
            PlayerServiceManager.sendPlayerMessage(
                    account,
                    Messages.buildPasswordTooLong()
            );
            return true;
        } else if (length < Configuration.minPasswordLength) {
            PlayerServiceManager.sendPlayerMessage(
                    account,
                    Messages.buildPasswordTooShort()
            );
            return true;
        }
        return false;
    }

    /**
     * Try to see if the authentication would work out.
     * <p>
     * We use an account to try to log in. If the two accounts match, we
     * know we can log in. And if we know we can log in, we actually go
     * ahead and log in.
     * </p>
     * <p>
     * We want to make sure to remove the player from the lock-down list,
     * assuming their authentication works. For some reason, this is
     * bugged, and I'm going to have to take a look at that. Oh well.
     * Can't wait.
     * </p>
     *
     * @param account the account to try
     * @return whether or not the authentication worked
     */
    public static boolean tryAuthentication(UserAccount account) {
        if (!AuthenticationM.data.accounts.containsKey(
                // No account has been created yet. Instead of changing
                // password or trying to log the player in, we have to create
                // a new account. Use AuthenticationHandler#registerAccount()
                // to hash the player's password and set it up in the data
                // container object.
                account.username.getUsername())) {
            registerAccount(account);
            return true;
        }
        if (AuthenticationM
                .data
                .authenticated
                .contains(account.username.getUsername())) {
            // The player already has an account. In addition to having an
            // account, they're logged into this account. Rather than trying
            // to create another account or something, just change the
            // password using AuthenticationHandler#changePassword().
            changedPassword(account);
            return true;
        } else {
            // The player has an account but is not yet authenticated.
            if (PlayerServiceManager.authenticatePlayer(account)) {
                // The authentication worked, yay! Run the
                // authenticationSucceeded function.
                authenticationSucceeded(account);
                return true;
            } else {
                // The authentication... well, it didn't work. How
                // disappointing. Run the authenticationFailed function.
                authenticationFailed(account);
                return false;
            }
        }
    }

    /**
     * Delete a user's account.
     * First we verify the user is indeed authenticated to ensure nothing bad
     * could possibly happen. Then, we actually delete the account. Sick, I
     * know, right? SO fucking cool. Too fucking cool.
     *
     * @param account the account to delete
     */
    public static void deleteAccount(UserAccount account) {
        if (!AuthenticationM
                .data
                .authenticated
                .contains(account.username.getUsername())) {
            return;
        }
        AuthenticationM.data.accounts.remove(
                account.username.getUsername()
        );
        PlayerServiceManager.kickPlayer(
                account,
                Messages.buildLoggedOut()
        );
    }

    /**
     * Logs a user out (incl. hostname)
     *
     * @param account the account to log out
     */
    public static void logOut(UserAccount account) {
        if (!AuthenticationM
                .data
                .authenticated
                .contains(account.username.getUsername())) {
            return;
        }
        UserAccount withoutHostname =
                AuthenticationM
                        .data
                        .accounts
                        .get(account.username.getUsername());
        withoutHostname.hostname.setHostname("");
        AuthenticationM.data.accounts.put(
                account.username.getUsername(),
                withoutHostname
        );
        PlayerServiceManager.kickPlayer(
                account,
                Messages.buildLoggedOut()
        );
    }
}
