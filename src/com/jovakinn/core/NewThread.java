package com.jovakinn.core;

import java.util.logging.Logger;

public class NewThread implements Runnable {

    private static Logger logger = Logger.getLogger(NewThread.class.getName());

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            logger.info( "\n" +"Thread " + Thread.currentThread().getId() + " was created..." );
        }

    }
}
