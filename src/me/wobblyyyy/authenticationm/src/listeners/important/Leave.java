package me.wobblyyyy.authenticationm.src.listeners.important;

import me.wobblyyyy.authenticationm.src.handlers.AuthenticationHandler;
import me.wobblyyyy.authenticationm.src.services.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leave implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLeave(PlayerQuitEvent event) {
        AuthenticationHandler.onPlayerDisconnect(
                Utils.getUserAccount(
                        event.getPlayer(),
                        ""
                )
        );
    }
}
