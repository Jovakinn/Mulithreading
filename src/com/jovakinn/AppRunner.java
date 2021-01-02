package com.jovakinn;

import com.jovakinn.core.NewThread;
import com.jovakinn.service.FileManagerService;
import com.jovakinn.service.WorkerThreads;
import java.io.ObjectStreamException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class AppRunner implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(AppRunner.class.getName());

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThreads("" + i);
            executorService.execute(worker);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        System.out.println("Finished all threads");


        Thread maxThread1 = new Thread();
        Thread maxThread2 = new Thread();
        Thread maxThread3 = new Thread();

        try {
            maxThread1.sleep(1000);
            LOGGER.info("I am " + Thread.currentThread().getName() + ", nice to meet you over there!");
            LOGGER.info("I have " + Thread.currentThread().getPriority() + " priority level.");
            LOGGER.info("I have " + Thread.currentThread().getId() + " id.");
            LOGGER.info("Am I a daemon? That's " + Thread.currentThread().isDaemon() + ".");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        maxThread1.start();
        maxThread2.start();
        maxThread3.start();

        FileManagerService fileManagerService = new FileManagerService();

        Thread thread = null;
        for (int i = 0; i < 5; i++) {
            thread = new Thread(new NewThread());
            thread.start();
        }

        try {
            FileManagerService.writeObject("threads.txt", thread);
        } catch (ObjectStreamException e) {
            LOGGER.info("We cannot serialize Thread.");
        }
    }

   @Override
   public void run() {}
}