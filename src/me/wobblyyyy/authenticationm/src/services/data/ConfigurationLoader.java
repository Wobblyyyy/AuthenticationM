package me.wobblyyyy.authenticationm.src.services.data;

import me.wobblyyyy.authenticationm.src.AuthenticationM;
import me.wobblyyyy.authenticationm.src.data.Configuration;

import java.io.File;
import java.util.HashMap;

import static me.wobblyyyy.authenticationm.src.services.data.AbstractLoader.getStringStringHashMap;

public class ConfigurationLoader {
    private String path = "config.json";
    private String dir;
    private File file;
    private AuthenticationM plugin;

    public ConfigurationLoader(AuthenticationM plugin) {
        dir = plugin.getDataFolder().getAbsolutePath();
        plugin.getDataFolder().mkdir();
        file = new File(
                dir +
                        File.separator +
                        path
        );
        this.plugin = plugin;
        Configuration.loadConfiguration(readConfiguration());
    }

    public HashMap<String, String> readConfiguration() {
        return getStringStringHashMap(file, plugin);
    }
}
