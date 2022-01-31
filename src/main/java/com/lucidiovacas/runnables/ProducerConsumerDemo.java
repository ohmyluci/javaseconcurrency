package com.lucidiovacas.runnables;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class SoupProducer extends Thread {
    private BlockingQueue servingLine;

    public SoupProducer(BlockingQueue servingLine) {
        this.servingLine = servingLine;
    }

    public void run(){
        for (int i=0; i<20; i++){
            try {
                servingLine.add("Bowl #" + i);
                System.out.format("Served Bowl #%d - Remaining queue capacitiy: %d\n", i, servingLine.remainingCapacity());
                Thread.sleep(100);
            } catch (Exception e) { e.printStackTrace(); }
        }
        servingLine.add("no more soup");
        servingLine.add("no more soup");
        servingLine.add("no more soup");
    }
}

class SoupConsumer extends Thread {

    private BlockingQueue servingLine;

    public SoupConsumer(BlockingQueue servingLine) {
        this.servingLine = servingLine;
    }

    @Override
    public void run() {
        while(true){
            try {
                String bowl = (String) servingLine.take();
                if(bowl.equals("no more soup")) break;
                System.out.format("Ate %s\n", bowl);
                Thread.sleep(300);
            } catch (Exception e){ e.printStackTrace(); }
        }
    }
}

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        BlockingQueue servingLine = new ArrayBlockingQueue<String>(5);
        new SoupConsumer(servingLine).start();
        new SoupConsumer(servingLine).start();
        new SoupConsumer(servingLine).start();
        new SoupProducer(servingLine).start();
    }
}
