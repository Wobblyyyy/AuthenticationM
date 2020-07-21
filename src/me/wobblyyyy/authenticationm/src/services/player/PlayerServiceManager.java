package me.wobblyyyy.authenticationm.src.services.player;

import me.wobblyyyy.authenticationm.src.AuthenticationM;
import me.wobblyyyy.authenticationm.src.data.DataContainer;
import me.wobblyyyy.authenticationm.src.data.player.UserAccount;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerServiceManager {
    /**
     * Send a player a message based on account
     *
     * @param account the player's account
     * @param message the message to send
     */
    public static void sendPlayerMessage(UserAccount account, String message) {
        sendPlayerMessage(getPlayer(account), message);
    }

    public static void sendPlayerMessage(Player player, String message) {
        player.sendMessage(ChatColor
                .translateAlternateColorCodes('&', message));
    }

    public static void sendPlayerMessage(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                message));
    }

    /**
     * Get a player based on their username.
     *
     * @param account the player's username
     * @return the player's player instance
     */
    public static Player getPlayer(String account) {
        return Bukkit.getServer().getPlayer(account);
    }

    /**
     * Gets a player based on their user account.
     *
     * @param account the player's account
     * @return the player's player instance
     */
    public static Player getPlayer(UserAccount account) {
        String username = account.username.getUsername();
        return getPlayer(username);
    }

    /**
     * Kick a player for an authentication related reason.
     *
     * @param player the player to kick
     */
    public static void kickPlayer(Player player) {
        player.kickPlayer("You were kicked for a reason related to " +
                "authentication.");
    }

    /**
     * Kick a player for a specified reason.
     *
     * @param player the player to kick
     * @param reason the reason they should be kicked
     */
    public static void kickPlayer(Player player, String reason) {
        reason = ChatColor.translateAlternateColorCodes(
                '&',
                reason
        );
        player.kickPlayer(reason);
    }

    /**
     * Same thing as the other kickPlayer method, but this time, we use a
     * user account instead of a player instance.
     *
     * @param account
     * @param reason
     */
    public static void kickPlayer(UserAccount account, String reason) {
        reason = ChatColor.translateAlternateColorCodes(
                '&',
                reason
        );
        Player player = getPlayer(account);
        player.kickPlayer(reason);
    }

    /**
     * Put the player on a lockdown mode.
     * While a player is locked down, they won't be able to move, eat,
     * interact, chat, use commands (other than authentication-related
     * commands), or anything else that could cause the player who the
     * account actually belongs to any harm.
     *
     * @param account the account to lock down
     * @param state   whether the account should be LOCKED DOWN (true) or NOT
     *                LOCKED DOWN (false)
     */
    public static void lockdownPlayer(UserAccount account, boolean state) {
        String username = account.username.getUsername();
        DataContainer data = AuthenticationM.data;
        if (state) {
            data.lockdown.add(username);
        } else {
            AuthenticationM.tasks.scheduleTask(
                    // We're having issues with removing players from
                    // lockdown for some reason - for now, using Bukkit's
                    // scheduling system to schedule the task to run a little
                    // bit later should help, but this is an issue we
                    // definitely have to correct at some point in the
                    // (hopefully) very near future.
                    () -> data.lockdown.remove(username),
                    10 // 10 ticks = 0.5 seconds
            );
        }
    }

    /**
     * Attempt to authenticate a player using a UserAccount instance.
     * If a whitelist or blacklist is active, make sure the player is either
     * on the whitelist or not on the blacklist. If they're not on the
     * whitelist, or they're on the blacklist, fail to authenticate them. If
     * the player doesn't meet the above qualifications, the player is kicked
     * from the server.
     *
     * @param account the account to try to authenticate
     * @return whether or not the player was actually authenticated
     */
    public static boolean authenticatePlayer(UserAccount account) {
        String username = account.username.getUsername();
        DataContainer data = AuthenticationM.data;
        if (data.accounts.containsKey(username)) {
            UserAccount reference = data.accounts.get(username);
            lockdownPlayer(account, !reference.isSameAccount(account));
            return reference.isSameAccount(account);
        } else {
            return false;
        }
    }

    public static boolean authenticatePlayerWithHostname(UserAccount account) {
        String username = account.username.getUsername();
        String password = "";
        String hostname = account.hostname.getHostname();
        DataContainer data = AuthenticationM.data;
        if (data.isWhitelistActive) {
            if (!data.isWhitelistBlacklist) {
                if (!data.whitelist.contains(username)) {
                    kickPlayer(getPlayer(account), "You're not whitelisted on" +
                            " this server!");
                    return false;
                }
            } else {
                if (data.whitelist.contains(username)) {
                    kickPlayer(getPlayer(account), "You're blacklisted on " +
                            "this server!");
                    return false;
                }
            }
        }
        if (data.accounts.containsKey(username)) {
            UserAccount reference = data.accounts.get(username);
            String referenceHostname = reference.hostname.getHostname();
            return hostname.equals(referenceHostname);
        }
        return false;
    }
}
