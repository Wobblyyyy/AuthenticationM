package me.wobblyyyy.authenticationm.src.data.player;

import java.io.Serializable;

public class Username implements Serializable {
    private String username;

    public Username(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsername(Username username) {
        this.username = username.getUsername();
    }

    public boolean isSameUsername(String username) {
        return this.username.equals(username);
    }

    public boolean isSameUsername(Username username) {
        return this.username.equals(username.getUsername());
    }
}
