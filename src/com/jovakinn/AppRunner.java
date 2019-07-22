package com.jovakinn;

import com.jovakinn.core.NewThread;

public class AppRunner {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new NewThread());
            thread.start();
        }
    }
}
