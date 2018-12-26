package com.lee.test.jdk11.jvm.map.skeleton;

import java.util.Iterator;

public class HashMap<K, V> implements Map<K, V> {


    @Override
    public void put(K k, V v) {

    }

    @Override
    public V get(K k) {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new Set<>() {
            Node<K, V>[] nodes;
            int index;

            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new Iterator<>() {
                    @Override
                    public boolean hasNext() {
                        return false;
                    }

                    @Override
                    public Entry<K, V> next() {
                        return null;
                    }
                };
            }
        };
    }

    class Node<K, V> {

    }


}
