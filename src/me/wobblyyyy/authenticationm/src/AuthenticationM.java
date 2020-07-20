package me.wobblyyyy.authenticationm.src;

import me.wobblyyyy.authenticationm.src.commands.ServerCommands;
import me.wobblyyyy.authenticationm.src.data.DataContainer;
import me.wobblyyyy.authenticationm.src.listeners.ServerListeners;
import me.wobblyyyy.authenticationm.src.services.data.ConfigurationLoader;
import me.wobblyyyy.authenticationm.src.services.data.DataExtractor;
import me.wobblyyyy.authenticationm.src.services.data.Io;
import me.wobblyyyy.authenticationm.src.services.data.MessagesLoader;
import me.wobblyyyy.authenticationm.src.services.server.AutosaveTask;
import me.wobblyyyy.authenticationm.src.services.server.Tasks;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class of the wonderful, the fabled, the mythical, the legendary,
 * AuthenticationM plugin! I know, it's incredible, and you're currently
 * sitting in your chair with your jaw on the fucking floor because you're
 * absolutely amazed at this incredible project.
 * <h1>To-do Board</h1>
 * <p>
 * TODO move TODO's to a seperate TODO file
 * TODO finish implementing configuration
 * TODO add language support for Spanish and maybe Evanerarien
 * </p>
 * <h1>Changelist</h1>
 * <h2>v1.0.0</h2>
 * <p>
 * Getting started with the plugin! And Git! As of now, we have a pretty
 * simple authentication plugin with a whitelist / blacklist, a lock-down
 * feature, and some pretty decently structured code.
 * </p>
 */
public class AuthenticationM extends JavaPlugin {
    /**
     * All of the serializable data we have to save.
     */
    public static DataContainer data;

    /**
     * Listeners we have to set up.
     */
    public static ServerListeners listeners;

    /**
     * Task generator thingy we have to set up.
     */
    public static Tasks tasks;

    /**
     * Server commands we have to set up.
     */
    public static ServerCommands commands;

    /**
     * Serializable data Input / Output instance.
     */
    public static Io io;

    /**
     * Object to load messages.
     */
    public static MessagesLoader messagesLoader;

    /**
     * Object to load the configuration.
     */
    public static ConfigurationLoader configurationLoader;

    /**
     * Object to extract default data from the res folder.
     * <p>
     * The very first time the plugin is loaded, we go in with the
     * expectation that none of the required resources are loaded, so we
     * extract them anyway. Completely and utterly fucking savage.
     * </p>
     */
    public static DataExtractor extractor;

    /**
     * All of our wonderful code to run whenever the plugin's enable method
     * is called. That's all. Yay!
     * <p>
     * This should probably be cleaned up a little bit just so it looks a
     * bit sexier.
     * </p>
     */
    @Override
    public void onEnable() {
        data = new DataContainer();
        listeners = new ServerListeners(this);
        tasks = new Tasks(this);
        commands = new ServerCommands(this);

        io = new Io(this);
        data = io.load();

        extractor = new DataExtractor(this);
        extractor.copyDefaultFiles();

        messagesLoader = new MessagesLoader(this);
        configurationLoader = new ConfigurationLoader(this);

        AutosaveTask.executeAutosaveTask();
    }

    /**
     * Equally wonderful code to be run whenever the plugin is disabled.
     */
    @Override
    public void onDisable() {
        io.save(data);
    }
}
