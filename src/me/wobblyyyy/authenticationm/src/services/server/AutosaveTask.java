package me.wobblyyyy.authenticationm.src.services.server;

import me.wobblyyyy.authenticationm.src.AuthenticationM;

public class AutosaveTask {
    public static void executeAutosaveTask() {
        AuthenticationM.io.save(AuthenticationM.data);
        AuthenticationM.tasks.scheduleTask(
                new Runnable() {
                    @Override
                    public void run() {
                        executeAutosaveTask();
                    }
                },
                12000
        );
    }
}
