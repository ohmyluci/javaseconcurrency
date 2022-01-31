package com.lucidiovacas.runnables;

import java.util.concurrent.locks.*;

class Shopper implements Runnable{
    static int garlicCount = 0;
    @Override
    public void run() {
        for (int i=0; i<10_000_000; i++){
            synchronized (Shopper.class){
                garlicCount++;
            }
        }
    }
//    static Lock pencil = new ReentrantLock();



//    private static synchronized void addGarlic(){
//        garlicCount++;
//    }
//
//    @Override
//    public void run() {
//        for(int i=0; i< 10_000_000; i++)
//            addGarlic();
//    }

//    @Override
//    public void run() {
//        pencil.lock();
//        for(int i=0; i<10_000; i++)
//            garlicCount++;
//        pencil.unlock();
//    }


}
public class DataRaceDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread luci = new Thread(new Shopper());
        Thread paula = new Thread(new Shopper());
        luci.start();
        paula.start();
        luci.join();
        paula.join();
        System.out.println("Buy " + Shopper.garlicCount);
    }

}
