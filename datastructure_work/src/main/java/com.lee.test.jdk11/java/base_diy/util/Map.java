package com.lee.test.jdk11.java.base_diy.util;

public interface Map<K, V> {

    interface Entry<K, V> {
        K getKey();

        V getValue();
    }

    V get(Object key);

    V put(K key, V value);


}
