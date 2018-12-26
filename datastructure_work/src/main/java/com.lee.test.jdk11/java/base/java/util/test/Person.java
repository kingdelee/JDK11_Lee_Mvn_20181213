package com.lee.test.jdk11.java.base.java.util.test;

public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hashcode = super.hashCode();
        if (name.startsWith("a")) {
            hashcode = 1;
        } else if (name.startsWith("b")) {
            hashcode = 17;
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

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    static final void tt() {

    }

}