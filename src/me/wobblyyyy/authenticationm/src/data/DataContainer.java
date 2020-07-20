package me.wobblyyyy.authenticationm.src.data;

import me.wobblyyyy.authenticationm.src.data.player.UserAccount;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Serializable class which contains a collection of permanent-ish data we
 * need to save if the plugin is shut down.
 * <p>
 * Rather than creating a custom method of saving data, we just use
 * Java's built-in serialization feature to read and write data to a file.
 * All of the IO is handled through a file named Io.java, this is just an
 * object which can contain all of the data we need to end up using.
 * </p>
 *
 * @author Colin Robertson
 */
public class DataContainer implements Serializable {
    /**
     * A hash-map which contains all of the user accounts.
     * <p>
     * Originally, this was an array-list, but I decided to use a HashMap
     * instead so you can easily get user accounts just based on the user
     * name.
     * </p>
     * <p>
     * The String represents the user's username.
     * The UserAccount represents the user's UserAccount instance.
     * </p>
     */
    public HashMap<String, UserAccount> accounts = new HashMap<>();

    /**
     * A list of players which should be locked down.
     */
    public ArrayList<String> lockdown = new ArrayList<>();

    /**
     * A list of whitelisted or blacklisted players.
     * <p>
     *     Depending on isWhitelistActive, this can either be a whitelist or
     *     a blacklist. In most cases, this will be a whitelist - who the
     *     fuck would want to use a blacklist - but anyway, yeah.
     * </p>
     */
    public ArrayList<String> whitelist = new ArrayList<>();

    /**
     * A list of players who have already been authenticated.
     * <p>
     *     We don't actually need this to be saved and loaded whenever the
     *     plugin is enabled or disabled - move this to another file?
     * </p>
     */
    public ArrayList<String> authenticated = new ArrayList<>();

    /**
     * Whether or not the whitelist is active.
     */
    public boolean isWhitelistActive;

    /**
     * Whether or not the whitelist is a blacklist instead.
     */
    public boolean isWhitelistBlacklist;
}
