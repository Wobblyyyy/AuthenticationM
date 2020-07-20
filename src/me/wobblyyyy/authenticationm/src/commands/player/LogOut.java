package me.wobblyyyy.authenticationm.src.commands.player;

import me.wobblyyyy.authenticationm.src.data.player.UserAccount;
import me.wobblyyyy.authenticationm.src.handlers.AuthenticationHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LogOut implements CommandExecutor {
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
        AuthenticationHandler.logOut(account);
        return false;
    }
}
