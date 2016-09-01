package com.elpassion.whyrx;

import java.util.concurrent.Executor;

public class DefaultExecutor implements Executor {
    public void execute(Runnable command) {
        command.run();
    }
}
