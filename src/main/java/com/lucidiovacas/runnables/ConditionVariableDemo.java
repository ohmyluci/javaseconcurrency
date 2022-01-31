package com.lucidiovacas.runnables;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class HungryPerson extends Thread {

    private int personId;
    private static Lock slowCockerLid = new ReentrantLock();
    private static int servings = 100;
    private static Condition soupTaken = slowCockerLid.newCondition();

    public HungryPerson(int personId) {
        this.personId = personId;
    }

    @Override
    public void run() {
        while(servings > 0) {
            slowCockerLid.lock();
            try{
                while((personId != servings % 25) && servings > 0) {
                    System.out.format("Person %d checked and it is waiting\n", personId);
                    soupTaken.await();
                }
                if(servings > 0){
                    servings--;
                    System.out.format("Person %d took some soup! Servings left: %d\n", personId, servings);
                    soupTaken.signalAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                slowCockerLid.unlock();
            }
        }
    }
}

public class ConditionVariableDemo {
    public static void main(String[] args) {
        for (int i=0; i<25; i++){
            new HungryPerson(i).start();
        }
    }
}
