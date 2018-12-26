package com.lee.test.jdk11.datastructure.mook.map;

public interface Map<K, V> {

    void put(K key, V value);

    V remove(K key);

    V getValue(K key);

    boolean contains(K key);

    boolean isEmpty();

    int size();


}
