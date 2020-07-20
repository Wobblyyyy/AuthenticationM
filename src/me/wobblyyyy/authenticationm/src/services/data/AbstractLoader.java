package me.wobblyyyy.authenticationm.src.services.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.wobblyyyy.authenticationm.src.AuthenticationM;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Abstract loading instance.
 */
public class AbstractLoader {
    /**
     * Get string-string HashMap from JSON using GSON.
     * @param file the file to use
     * @param plugin the plugin to use (always authentication plugin)
     * @return a hash-map, string-string
     */
    public static HashMap<String, String> getStringStringHashMap(
            File file,
            AuthenticationM plugin) {
        try {
            String fileContents = new String(Files.readAllBytes(Paths.get(
                    file.getAbsolutePath()
            )));
            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String, String>>() {
            }.getType();
            HashMap<String, String> map = gson.fromJson(fileContents, type);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
