package me.wobblyyyy.authenticationm.src.data;

import java.util.HashMap;

public class Messages {
    public static String prefix = "&cAuthentication &f| &7";
    public static String welcome = "Welcome! Please remember to authenticate " +
            "yourself with your password.";
    public static String tooManyArgs = "Too many arguments supplied!";
    public static String tooFewArgs = "Too few arguments supplied!";
    public static String invalidArg = "Invalid argument!";
    public static String loginSucceeded = "You've successfully logged in.";
    public static String autoLogin = "You've been automatically logged in.";
    public static String loginFailed = "Failed to authenticate your account.";
    public static String badPassword = "Incorrect password.";
    public static String passwordChanged = "You've successfully changed your " +
            "password.";
    public static String accountCreated = "You've successfully created an " +
            "account.";
    public static String authFormat = "Format: /auth <password>";
    public static String loggedOut = "You've been logged out.";
    public static String whitelistOn = "The whitelist has been enabled.";
    public static String whitelistOff = "The whitelist has been disabled.";
    public static String whitelistAdd = "You've added that player to the " +
            "whitelist.";
    public static String whitelistRemove = "You've removed that player from " +
            "the whitelist.";
    public static String passwordTooShort = "Your password is too short!";
    public static String passwordTooLong = "Your password is too long!";

    public static void loadMessages(HashMap<String, String> messages) {
        if (!Configuration.useCustomMessages) return;
        prefix = messages.get("prefix");
        welcome = messages.get("welcome");
        tooManyArgs = messages.get("tooManyArgs");
        tooFewArgs = messages.get("tooFewArgs");
        invalidArg = messages.get("invalidArg");
        loginSucceeded = messages.get("loginSucceeded");
        autoLogin = messages.get("autoLogin");
        loginFailed = messages.get("loginFailed");
        badPassword = messages.get("badPassword");
        passwordChanged = messages.get("passwordChanged");
        accountCreated = messages.get("accountCreated");
        authFormat = messages.get("authFormat");
        loggedOut = messages.get("loggedOut");
        whitelistOn = messages.get("whitelistOn");
        whitelistOff = messages.get("whitelistOff");
        whitelistAdd = messages.get("whitelistAdd");
        whitelistRemove = messages.get("whitelistRemove");
        passwordTooShort = messages.get("passwordTooShort");
        passwordTooLong = messages.get("passwordTooLong");
    }

    public static String buildWelcome() {
        return prefix + welcome;
    }

    public static String buildTooManyArgs() {
        return prefix + tooManyArgs;
    }

    public static String buildTooFewArgs() {
        return prefix + tooFewArgs;
    }

    public static String buildInvalidArg() {
        return prefix + invalidArg;
    }

    public static String buildLoginSucceeded() {
        return prefix + loginSucceeded;
    }

    public static String buildAutoLogin() {
        return prefix + autoLogin;
    }

    public static String buildLoginFailed() {
        return prefix + loginFailed;
    }

    public static String buildBadPassword() {
        return prefix + badPassword;
    }

    public static String buildPasswordChanged() {
        return prefix + passwordChanged;
    }

    public static String buildAccountCreated() {
        return prefix + accountCreated;
    }

    public static String buildAuthFormat() {
        return prefix + authFormat;
    }

    public static String buildLoggedOut() {
        return prefix + loggedOut;
    }

    public static String buildWhitelistOn() {
        return prefix + whitelistOn;
    }

    public static String buildWhitelistOff() {
        return prefix + whitelistOff;
    }

    public static String buildWhitelistAdd() {
        return prefix + whitelistAdd;
    }

    public static String buildWhitelistRemove() {
        return prefix + whitelistRemove;
    }

    public static String buildPasswordTooShort() {
        return prefix + passwordTooShort;
    }

    public static String buildPasswordTooLong() {
        return prefix + passwordTooLong;
    }
}
