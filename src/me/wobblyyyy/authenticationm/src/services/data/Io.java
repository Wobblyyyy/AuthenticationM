package me.wobblyyyy.authenticationm.src.services.data;

import me.wobblyyyy.authenticationm.src.AuthenticationM;
import me.wobblyyyy.authenticationm.src.data.DataContainer;

import java.io.*;
import java.util.ArrayList;

/**
 * Data input and output object.
 *
 * @author Colin Robertson
 */
public class Io {
    /**
     * The file to save and load data to and from.
     */
    private File file;

    /**
     * Constructor functionality to set up the io functionality.
     *
     * @param authenticationM plugin instance
     */
    public Io(AuthenticationM authenticationM) {
        String directory = authenticationM.getDataFolder().getAbsolutePath();
        String path = "AuthenticationM.data";
        authenticationM.getDataFolder().mkdir();
        file = new File(
                directory +
                        File.separator +
                        path
        );
    }

    /**
     * Save data to a file. Woah!
     *
     * @param dataContainer TODO remove this?
     */
    public void save(DataContainer dataContainer) {
        DataContainer data = AuthenticationM.data;
        data.authenticated = new ArrayList<>();
        data.lockdown = new ArrayList<>();
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream =
                    new FileOutputStream(file);
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load data from a file. Equally woah!
     *
     * @return data which was loaded
     */
    public DataContainer load() {
        DataContainer data = new DataContainer();
        try {
            FileInputStream fileInputStream =
                    new FileInputStream(file);
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(fileInputStream);
            data = (DataContainer) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
