package com.lee.test.jdk11.datastructure.mook.union_find;

public interface UF {

    boolean isConnected(int a, int b);

    void unionElements(int a, int b);

    int getSize();
}
