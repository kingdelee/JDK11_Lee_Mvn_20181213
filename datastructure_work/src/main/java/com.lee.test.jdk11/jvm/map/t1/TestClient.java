package com.lee.test.jdk11.jvm.map.t1;

public class TestClient {

    public static void main(String[] args) {
        A a = new B();

    }

}

class A {

}

class B extends A {
    public void t1() {

    }
}

class C {
    public void t1() {

    }
}
