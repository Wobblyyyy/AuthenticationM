package me.wobblyyyy.authenticationm.src.listeners.cancel;

import me.wobblyyyy.authenticationm.src.listeners.Shared;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;

public class Chat implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (Shared.isPlayerOnLockdown(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        if (Shared.isPlayerOnLockdown(event.getPlayer())) {
            ArrayList<String> allowedCommands = new ArrayList<>() {{
                add("auth");
                add("logout");
                add("signout");
                add("log_out");
                add("authlogout");
                add("authsignout");
                add("remaccount");
                add("delaccount");
                add("remacc");
                add("delacc");
                add("delete");
            }};
            for (String c : allowedCommands) {
                c = "/" + c;
                if (event.getMessage().startsWith(c)) return;
            }
            event.setCancelled(true);
        }
    }
}
