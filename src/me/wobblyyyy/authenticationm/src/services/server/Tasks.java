package me.wobblyyyy.authenticationm.src.services.server;

import me.wobblyyyy.authenticationm.src.AuthenticationM;
import org.bukkit.Bukkit;

public class Tasks {
    private AuthenticationM plugin;

    public Tasks(AuthenticationM plugin) {
        this.plugin = plugin;
    }

    public void scheduleAsyncTask(Runnable runnable, long time) {
        // NOT IMPLEMENTED YET
        scheduleTask(runnable, time);
    }

    public void scheduleTask(Runnable runnable, long time) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(
                plugin,
                runnable,
                time
        );
    }
}
