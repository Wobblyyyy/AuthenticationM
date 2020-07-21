package me.wobblyyyy.authenticationm.src.data;

import me.wobblyyyy.authenticationm.src.AuthenticationM;

import java.util.HashMap;

/**
 * A beautiful class representing our configuration.
 *
 * @author Colin Robertson
 */
public class Configuration {
    /**
     * Whether or not the whitelist is enabled.
     */
    public static boolean whitelistIsEnabled = false;

    /**
     * Whether or not the whitelist is a blacklist instead.
     */
    public static boolean whitelistIsBlacklist = false;

    /**
     * Should data be automatically saved?
     */
    public static boolean autoSaveData = true;

    /**
     * Should we automatically check for updates?
     */
    public static boolean automaticallyCheckForUpdates = true;

    /**
     * Should we use custom messages or the default ones?
     * <p>
     * If this is set to false, we don't load messages with the
     * MessagesLoader thingy. That's all! Yay!
     * </p>
     */
    public static boolean useCustomMessages = true;

    /**
     * Should we enable automatically logging users in if their hostname
     * matches their last-used hostname?
     */
    public static boolean enableAutoLogin = true;

    /**
     * Should we restrict the user chatting?
     */
    public static boolean restrictChat = true;

    /**
     * Should we restrict commands?
     */
    public static boolean restrictCommands = true;

    /**
     * Should we restrict the user from using commands?
     */
    public static boolean restrictMovement = true;

    /**
     * Should we restrict the user from interacting?
     */
    public static boolean restrictInteractions = true;

    /**
     * Should we kick players who don't authenticate themselves quickly enough?
     */
    public static boolean kickPlayersWhoDontAuthenticate = true;

    /**
     * How long (in seconds) it should be before we kick the user.
     */
    public static int kickDelay = 30;

    /**
     * What's the minimum length of the password?
     */
    public static int minPasswordLength = 5;

    /**
     * What's the maximum length of the password?
     */
    public static int maxPasswordLength = 30;

    /**
     * How many rounds of salting we should go through (adjusts security)
     * <p>
     * WARNING: don't make this too high! < 12 might crash your server or
     * something
     * </p>
     */
    public static int saltRounds = 5;

    /**
     * Should we welcome the user when they join?
     */
    public static boolean welcomeOnJoin = true;

    /**
     * Get boolean from string.
     *
     * @param e the string to get a boolean from
     * @return whether or not the returned value is "true"
     */
    public static boolean booleanFromString(String e) {
        return e.equalsIgnoreCase("true");
    }

    /**
     * Get an integer from string.
     *
     * @param e the string to get an integer from
     * @return the integer
     */
    public static int intFromString(String e) {
        try {
            return Integer.parseInt(e);
        } catch (Exception ex) {
            System.out.println("Invalid INTEGER in AuthenticationM config.");
        }
        return 0;
    }

    /**
     * Get data from a configuration map loaded from a file and do magical
     * things with it.
     *
     * @param map hash-map to use
     */
    public static void loadConfiguration(HashMap<String, String> map) {
        whitelistIsEnabled = booleanFromString(map.get("whitelistIsEnabled"));
        whitelistIsBlacklist = booleanFromString(map.get(
                "whitelistIsBlacklist"));
        autoSaveData = booleanFromString(map.get("autoSaveData"));
        automaticallyCheckForUpdates = booleanFromString(map.get(
                "automaticallyCheckForUpdates"));
        useCustomMessages = booleanFromString(map.get("useCustomMessages"));
        enableAutoLogin = booleanFromString(map.get("enableAutoLogin"));
        restrictChat = booleanFromString(map.get("restrictChat"));
        restrictCommands = booleanFromString(map.get("restrictCommands"));
        restrictMovement = booleanFromString(map.get("restrictMovement"));
        restrictInteractions = booleanFromString(map.get(
                "restrictInteractions"));
        kickPlayersWhoDontAuthenticate = booleanFromString(map.get(
                "kickPlayersWhoDontAuthenticate"));
        kickDelay = intFromString(map.get("kickDelay"));
        minPasswordLength = intFromString(map.get("minPasswordLength"));
        maxPasswordLength = intFromString(map.get("maxPasswordLength"));
        saltRounds = intFromString(map.get("saltRounds"));
        welcomeOnJoin = booleanFromString(map.get("welcomeOnJoin"));
    }

    /**
     * Configure the plugin. Should be run after loadConfiguration.
     */
    public static void configure() {
        AuthenticationM.data.isWhitelistActive = whitelistIsEnabled;
        AuthenticationM.data.isWhitelistBlacklist = whitelistIsBlacklist;
    }
}
