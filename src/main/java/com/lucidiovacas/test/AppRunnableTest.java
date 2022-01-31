package com.lucidiovacas.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AppRunnableTest {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            try(BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Projects\\Concurrency\\sample.txt")))){
                String line = null;
                while((line=reader.readLine())!=null){
                    System.out.println(Thread.currentThread().getName() + " reading the line: " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

    }
}
