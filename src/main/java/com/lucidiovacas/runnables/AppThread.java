package com.lucidiovacas.runnables;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AppThread extends Thread{

    @Override
    public void run() {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Projects\\Concurrency\\sample.txt")))){
            String line = null;
            while((line=reader.readLine())!=null){
                System.out.println(Thread.currentThread().getName() + " reading the line: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
