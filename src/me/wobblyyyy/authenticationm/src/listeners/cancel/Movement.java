package me.wobblyyyy.authenticationm.src.listeners.cancel;

import me.wobblyyyy.authenticationm.src.data.Configuration;
import me.wobblyyyy.authenticationm.src.listeners.Shared;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Movement implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!Configuration.restrictMovement) return;
        if (Shared.isPlayerOnLockdown(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}
