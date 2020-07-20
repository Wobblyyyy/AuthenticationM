package me.wobblyyyy.authenticationm.src.data.player;

import java.io.Serializable;

public class Hostname implements Serializable {
    private String hostname;

    public Hostname(String hostname) {
        this.hostname = hostname;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setHostname(Hostname hostname) {
        this.hostname = hostname.getHostname();
    }

    public boolean isSameHostname(String hostname) {
        return this.hostname.equals(hostname);
    }

    public boolean isSameHostname(Hostname hostname) {
        return this.hostname.equals(hostname.getHostname());
    }
}
