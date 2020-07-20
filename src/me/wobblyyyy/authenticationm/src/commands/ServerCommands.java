package me.wobblyyyy.authenticationm.src.commands;

import me.wobblyyyy.authenticationm.src.AuthenticationM;
import me.wobblyyyy.authenticationm.src.commands.admin.Whitelist;
import me.wobblyyyy.authenticationm.src.commands.player.Auth;
import me.wobblyyyy.authenticationm.src.commands.player.DeleteAccount;
import me.wobblyyyy.authenticationm.src.commands.player.LogOut;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;

import java.util.HashMap;

public class ServerCommands {
    public ServerCommands(AuthenticationM plugin) {
        HashMap<String, CommandExecutor> map = new HashMap<>() {{
            put("auth", new Auth());
            put("logout", new LogOut());
            put("deleteaccount", new DeleteAccount());
            put("whitelist", new Whitelist());
        }};
        registerCommands(map);
    }

    public void registerCommands(HashMap<String, CommandExecutor> map) {
        for (HashMap.Entry<String, CommandExecutor> entry : map.entrySet()) {
            String command = entry.getKey();
            CommandExecutor executor = entry.getValue();
            Bukkit.getServer().getPluginCommand(command).setExecutor(executor);
        }
    }
}
