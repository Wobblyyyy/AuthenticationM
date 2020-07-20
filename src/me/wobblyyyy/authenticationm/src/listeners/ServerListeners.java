package me.wobblyyyy.authenticationm.src.listeners;

import me.wobblyyyy.authenticationm.src.AuthenticationM;
import me.wobblyyyy.authenticationm.src.listeners.cancel.Block;
import me.wobblyyyy.authenticationm.src.listeners.cancel.Chat;
import me.wobblyyyy.authenticationm.src.listeners.cancel.Entity;
import me.wobblyyyy.authenticationm.src.listeners.cancel.Movement;
import me.wobblyyyy.authenticationm.src.listeners.important.Join;
import me.wobblyyyy.authenticationm.src.listeners.important.Leave;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ServerListeners {
    public ServerListeners(AuthenticationM plugin) {
        ArrayList<Listener> listeners = new ArrayList<>() {{
            add(new Block());
            add(new Entity());
            add(new Movement());
            add(new Chat());

            add(new Join());
            add(new Leave());
        }};
        registerEvents(listeners, plugin);
    }

    public void registerEvents(List<Listener> list, JavaPlugin p) {
        for (Listener l : list) {
            Bukkit
                    .getServer()
                    .getPluginManager()
                    .registerEvents(l, p);
        }
    }
}
