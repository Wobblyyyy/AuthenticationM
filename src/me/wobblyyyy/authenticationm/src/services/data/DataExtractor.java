package me.wobblyyyy.authenticationm.src.services.data;

import me.wobblyyyy.authenticationm.src.AuthenticationM;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

public class DataExtractor {
    private AuthenticationM plugin;

    public DataExtractor(AuthenticationM plugin) {
        this.plugin = plugin;
    }

    public void copyDefaultFiles() {
        String df = plugin.getDataFolder().getAbsolutePath() + File.separator;
        HashMap<String, File> data = new HashMap<>() {{
           put(
                   "/me/wobblyyyy/authenticationm/res/messages.json",
                   new File(df + "messages.json")
           );
           put(
                   "/me/wobblyyyy/authenticationm/res/config.json",
                   new File(df + "config.json")
           );
        }};
        for (HashMap.Entry<String, File> entry : data.entrySet()) {
            String s = entry.getKey();
            File f = entry.getValue();
            if (!f.exists()) {
                try {
                    URL i = getClass().getResource(s);
                    FileUtils.copyURLToFile(i, f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
