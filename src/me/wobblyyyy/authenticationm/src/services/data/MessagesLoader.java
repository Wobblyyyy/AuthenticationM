package me.wobblyyyy.authenticationm.src.services.data;

import me.wobblyyyy.authenticationm.src.AuthenticationM;
import me.wobblyyyy.authenticationm.src.data.Messages;

import java.io.File;
import java.util.HashMap;

import static me.wobblyyyy.authenticationm.src.services.data.AbstractLoader.getStringStringHashMap;

public class MessagesLoader {
    private String path = "messages.json";
    private String dir;
    private File file;
    private AuthenticationM plugin;

    public MessagesLoader(AuthenticationM plugin) {
        dir = plugin.getDataFolder().getAbsolutePath();
        plugin.getDataFolder().mkdir();
        file = new File(
                dir +
                        File.separator +
                        path
        );
        this.plugin = plugin;
        Messages.loadMessages(readMessages());
    }

    public HashMap<String, String> readMessages() {
        return getStringStringHashMap(file, plugin);
    }
}
