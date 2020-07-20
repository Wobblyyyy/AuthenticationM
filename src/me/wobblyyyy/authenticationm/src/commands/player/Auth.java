package me.wobblyyyy.authenticationm.src.commands.player;

import me.wobblyyyy.authenticationm.src.data.Messages;
import me.wobblyyyy.authenticationm.src.data.player.UserAccount;
import me.wobblyyyy.authenticationm.src.handlers.AuthenticationHandler;
import me.wobblyyyy.authenticationm.src.services.player.PlayerServiceManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Auth implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        UserAccount account = new UserAccount(
                player.getName(),
                "",
                player.getAddress().getHostName()
        );
        if (args.length > 1) {
            PlayerServiceManager.sendPlayerMessage(
                    account,
                    Messages.buildTooManyArgs()
            );
        } else if (args.length == 1) {
            String password = args[0];
            account.password.setPassword(password);
            AuthenticationHandler.tryAuthentication(account);
        } else {
            PlayerServiceManager.sendPlayerMessage(
                    account,
                    Messages.buildTooFewArgs()
            );
            PlayerServiceManager.sendPlayerMessage(
                    account,
                    Messages.buildAuthFormat()
            );
        }
        return false;
    }
}
