package me.wobblyyyy.authenticationm.src.commands.admin;

import me.wobblyyyy.authenticationm.src.AuthenticationM;
import me.wobblyyyy.authenticationm.src.data.Messages;
import me.wobblyyyy.authenticationm.src.services.player.PlayerServiceManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Whitelist implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {
        switch (args.length) {
            case 0:
                PlayerServiceManager.sendPlayerMessage(
                        sender,
                        Messages.buildTooFewArgs()
                );
                break;
            case 1:
                switch (args[0]) {
                    case "enable":
                        AuthenticationM.data.isWhitelistActive = true;
                        PlayerServiceManager.sendPlayerMessage(
                                sender,
                                Messages.buildWhitelistOn()
                        );
                        break;
                    case "disable":
                        AuthenticationM.data.isWhitelistActive = false;
                        PlayerServiceManager.sendPlayerMessage(
                                sender,
                                Messages.buildWhitelistOff()
                        );
                        break;
                    default:
                        PlayerServiceManager.sendPlayerMessage(
                                sender,
                                Messages.buildInvalidArg()
                        );
                        break;
                }
                break;
            case 2:
                switch (args[0]) {
                    case "add":
                        AuthenticationM
                                .data
                                .whitelist
                                .add(args[1]);
                        PlayerServiceManager.sendPlayerMessage(
                                sender,
                                Messages.buildWhitelistAdd()
                        );
                        break;
                    case "remove":
                        AuthenticationM
                                .data
                                .whitelist
                                .remove(args[1]);
                        PlayerServiceManager.sendPlayerMessage(
                                sender,
                                Messages.buildWhitelistRemove()
                        );
                        break;
                    default:
                        PlayerServiceManager.sendPlayerMessage(
                                sender,
                                Messages.buildInvalidArg()
                        );
                }
                break;
            default:
                PlayerServiceManager.sendPlayerMessage(
                        sender,
                        Messages.buildTooManyArgs()
                );
        }
        return false;
    }
}
