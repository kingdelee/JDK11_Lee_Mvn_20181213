package com.lee.test.jdk11.classloader.test4.com.example;

public class Sample {
    private Sample instance;

    public void setSample(Object instance) {
        this.instance = (Sample) instance;
    }
}
