package com.lee.test.jdk11.jvm.map.t1;

public class Main {
    public static void main(String[] args) {
        int[] a = new int[2];
        System.out.println(a);
//        int i = a.length;
//        new A();
        System.out.println(t1());


    }

    public static int t1() {
        try {
            return 1;
        } finally {
            return 2;
        }
    }

}


 