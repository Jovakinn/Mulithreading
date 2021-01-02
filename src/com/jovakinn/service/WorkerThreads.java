package com.jovakinn.service;

public class WorkerThreads implements Runnable {

    private String message;

    public WorkerThreads(String str) {
        this.message = str;
    }
    public void processManager(){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        System.out.println(Thread.currentThread().getName() + "(Start) message = " + message);
        processManager();
        System.out.println(Thread.currentThread().getName() + "(End)");
    }
}
