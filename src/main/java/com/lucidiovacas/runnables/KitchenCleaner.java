package com.lucidiovacas.runnables;

public class KitchenCleaner implements Runnable{
    @Override
    public void run() {
        while(true){
            System.out.println("Cleaning garbage...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread cleaner = new Thread(new KitchenCleaner());
        cleaner.setDaemon(true);
        cleaner.start();

        System.out.println("working on my things");
        Thread.sleep(500);
        System.out.println("working on my things");
        Thread.sleep(500);
        System.out.println("working on my things");
        Thread.sleep(500);
        System.out.println("working on my things");
        Thread.sleep(500);
        System.out.println("working on my things");
        Thread.sleep(500);
    }
}
