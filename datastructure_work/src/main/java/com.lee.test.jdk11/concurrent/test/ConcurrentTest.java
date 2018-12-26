package com.lee.test.jdk11.concurrent.test;

import org.junit.jupiter.api.Test;

public class ConcurrentTest {


    @Test
    public void t1() throws InterruptedException {

        Person person1 = new Person(1);

        new Thread(() -> {
            person1.add();
        }, "t1").start();


        new Thread(() -> {
            person1.add();
        }, "t2").start();

        new Thread(() -> {
            person1.add();
        }, "t3").start();

        System.out.println("end");
    }

    public static void main(String[] args) throws InterruptedException {
        Person person1 = new Person(1);

        new Thread(() -> {
            person1.add();
        }, "t1").start();

        new Thread(() -> {
            person1.add();
        }, "t2").start();

        new Thread(() -> {
            person1.add();
        }, "t3").start();

        System.out.println("end");
    }

}

class Person {

    private int id;

    public Person(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public synchronized void add() {
        System.out.println(Thread.currentThread().getName());
        if (Thread.currentThread().getName().equals("t1")) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        id++;
        System.out.println(Thread.currentThread().getName() + "," + id);
    }

}