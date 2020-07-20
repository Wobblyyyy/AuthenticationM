package me.wobblyyyy.authenticationm.src.listeners;

import me.wobblyyyy.authenticationm.src.AuthenticationM;
import org.bukkit.entity.Player;

public class Shared {
    public static boolean isPlayerOnLockdown(Player player) {
        return AuthenticationM.data.lockdown.contains(player.getName());
    }
}
