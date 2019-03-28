package com.lee.test.jdk11.jvm.test;

public class CpuTest {
 
    public static void main(String[] args) throws InterruptedException {
        loop();
        endlessLoop();
    }
 
 
    public static void endlessLoop() throws InterruptedException {
 
        while (true) {
            System.out.println("hello world! loop!");
        }
    }
 
    public static void loop(){
        for (int i = 0; i < 10000; i++) {
            System.out.println("hello world! endless loop!");
        }
    }
}