package com.elpassion.whyrx;

import java.util.concurrent.Executor

class SynchronousExecutor : Executor {
    override fun execute(command: Runnable) {
        command.run()
    }
}