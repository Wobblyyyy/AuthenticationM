package me.wobblyyyy.authenticationm.src.data.player;

import java.io.Serializable;

public class Password implements Serializable {
    /**
     * A string representing the password. Woo-hoo.
     */
    private String password;

    /**
     * Constructor with password.
     * @param password the password to use
     */
    public Password(String password) {
        this.password = password;
    }

    /**
     * Gets the password.
     * <p>
     *     Note that the value this returns has already been hashed.
     * </p>
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * <p>
     *     Make sure to use BCrypt to encrypt the password!
     * </p>
     * @param password the string password to encrypt and set
     */
    public void setPassword(String password) {
//        this.password = BCrypt.hashpw(
//                password,
//                BCrypt.gensalt(Configuration.saltRounds)
//        );
        /*
         * TODO
         *  I removed BCrypt because for some reason I'm having issues with
         *  hashing passwords. It's important that we re-add this feature
         *  before major public releases so this plugin maintains security.
         */
        this.password = password;
    }

    /**
     * Use BCrypt's checkpw function to check whether or not the same
     * password is being used.
     * @param password password to try
     * @return whether or not the passwords match
     */
    public boolean isSamePassword(Password password) {
        return this.password.equals(password.getPassword());
        /*
         * TODO
         *  I removed BCrypt because for some reason I'm having issues with
         *  hashing passwords. It's important that we re-add this feature
         *  before major public releases so this plugin maintains security.
         */
//        return BCrypt.checkpw(
//                password.getPassword(),
//                this.password
//        );
    }
}
