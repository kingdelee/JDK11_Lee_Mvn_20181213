package com.wangwenjun.concurrent.chapter03;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {
        //
        List<Thread> threads = IntStream.range(1, 3)
                .mapToObj(ThreadJoin::create).collect(toList());

        //
//        threads.forEach(Thread::start);


//        for (Thread thread : threads)
//        {
//            System.out.println("-----:" + thread.getName());
//            thread.join();
//            System.out.println("-----:" + thread.getName());
//        }


//        for (int i = 0; i < 10; i++) {
//            System.out.println(Thread.currentThread().getName() + "#" + i);
//            shortSleep();
//        }

        //
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "name1");
        thread.start();

        TimeUnit.MILLISECONDS.sleep(2);
        thread.join();
        System.out.println("===============");
    }

    private static Thread create(int seq) {
        return new Thread(() ->
        {
            if ("1".equals(Thread.currentThread().getName())) {
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());

            }

            if ("2".equals(Thread.currentThread().getName())) {
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());

            }


//            for (int i = 0; i < 10; i++) {
//                System.out.println(Thread.currentThread().getName() + "#" + i);
//                shortSleep();
//            }
        }, String.valueOf(seq));
    }

    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}