package me.wobblyyyy.authenticationm.src.services;

import me.wobblyyyy.authenticationm.src.data.player.UserAccount;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Utils {
    public static UserAccount getUserAccount(Player player, String password) {
        return new UserAccount(
                player.getName(),
                password,
                Objects.requireNonNull(player.getAddress()).getHostName()
        );
    }
}
