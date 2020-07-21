package me.wobblyyyy.authenticationm.src.listeners.cancel;

import me.wobblyyyy.authenticationm.src.data.Configuration;
import me.wobblyyyy.authenticationm.src.listeners.Shared;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Block implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!Configuration.restrictInteractions) return;
        if (Shared.isPlayerOnLockdown(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(BlockBreakEvent event) {
        if (!Configuration.restrictInteractions) return;
        if (Shared.isPlayerOnLockdown(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}
