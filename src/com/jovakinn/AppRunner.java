package com.jovakinn;

import com.jovakinn.core.NewThread;
import com.jovakinn.service.FileManagerService;

public class AppRunner {

    public static void main(String[] args) {

        FileManagerService fileManagerService = new FileManagerService();

        Thread thread = null;
        for (int i = 0; i < 5; i++) {
            thread = new Thread(new NewThread());
            thread.start();
        }
        fileManagerService.writeObject("threads.txt", thread);
    }
}
