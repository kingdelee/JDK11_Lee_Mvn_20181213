package com.lee.test.jdk11.java.base_diy.util.test;

public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hashcode = super.hashCode();
        if (name.startsWith("a")) {
            hashcode = 2;
        } else if (name.startsWith("b")) {
            hashcode = 3;
        } else if (name.startsWith("c")) {
            hashcode = 4;
        }
        return hashcode;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            String name = ((Person) obj).getName();
            if (this.getName().equals(name)) {
                return true;
            }
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}