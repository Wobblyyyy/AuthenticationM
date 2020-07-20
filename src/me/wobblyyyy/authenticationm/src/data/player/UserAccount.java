package me.wobblyyyy.authenticationm.src.data.player;

import java.io.Serializable;

public class UserAccount implements Serializable {
    public Username username;
    public Password password;
    public Hostname hostname;

    public UserAccount(String username, String password, String hostname) {
        this.username = new Username(username);
        this.password = new Password(password);
        this.hostname = new Hostname(hostname);
    }

    public boolean isSameAccount(UserAccount account) {
        Username tryUsername = account.username;
        Password tryPassword = account.password;
        return (
                username.isSameUsername(tryUsername) &&
                        password.isSamePassword(tryPassword));
    }

    public boolean isSameAccountWithHostname(UserAccount account) {
        Username tryUsername = account.username;
        Password tryPassword = account.password;
        Hostname tryHostname = account.hostname;
        return (
                username.isSameUsername(tryUsername) &&
                        password.isSamePassword(tryPassword) &&
                        hostname.isSameHostname(tryHostname));
    }
}
