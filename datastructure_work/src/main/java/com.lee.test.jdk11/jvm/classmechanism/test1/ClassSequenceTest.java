package com.lee.test.jdk11.jvm.classmechanism.test1;


import org.junit.jupiter.api.Test;

class A {

    static int a;

    static int b = 100;

    static {
        int b = 10;
        System.out.println("A static blocks:" + b);
    }

    {
        b++;
        System.out.println("A common blocks:" + b);
    }

    public A() {
        b++;
        System.out.println("A():" + b);
    }

    public void dosth() {
        System.out.println("A dosth():" + b);
    }

    static class AInner {
        static int a = 0;

        static {
            int b = 10;
            System.out.println("AInner static blocks:" + b);
        }

        public void dosth() {
            System.out.println("AInner dosth:" + b);
        }
    }
}

class B extends A {
    static int a;

    static int b = 100;

    static {
        int b = 10;
        System.out.println("B static blocks:" + b);
    }

    {
        b++;
        System.out.println("B common blocks:" + b);
    }

    public B() {
        b++;
        System.out.println("B():" + b);
    }

    public void dosth() {
        System.out.println("B dosth():" + b);
    }

    static class BInner {
        static int a = 0;

        static {
            int b = 10;
            System.out.println("BInner static blocks:" + b);
        }

        public void dosth() {
            System.out.println("BInner dosth:" + b);
        }
    }
}

public class ClassSequenceTest {

    @Test
    public void t1() {
        B b = new B();
        b.dosth();
    }

    @Test
    public void t2() {
        System.out.println(B.a);
    }

    @Test
    public void t3() {
        A a = new B();
        a.dosth();
    }

    @Test
    public void t4() {
        System.out.println(A.AInner.a);
    }

    @Test
    public void t5() {
        System.out.println(B.BInner.a);
    }

    @Test
    public void t6() {
        A.AInner aInner = new A.AInner();
        aInner.dosth();
    }

    @Test
    public void t7() {
        B.BInner bInner = new B.BInner();
        bInner.dosth();
    }

}

