package com.lee.test.jdk11.jvm.map.t1;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;


public class HashMapTest1 {


    /**
     * t1目标：能对指定目标通过put 和 get实现结果一致
     * 将[1-16]put进去，能够依次拿出来
     */
    @Test
    public void t1() {

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        IntStream.rangeClosed(1, 16).forEach(i -> hashMap.put(i, i));
        IntStream.rangeClosed(1, 16).forEach(i -> System.out.println(hashMap.get(i)));

    }

    /**
     * 问题：hashcode算法有问题，抛出异常：
     * java.lang.ArrayIndexOutOfBoundsException: Index 96321 out of bounds for length 16
     */
    @Test
    public void t2() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("aaa", "1");
        hashMap.put("bbb", "2");
        hashMap.put("ccc", "3");
        hashMap.put("ddd", "4");

        System.out.println(hashMap.get("ccc"));

    }


}

interface Map<K, V> {
    void put(K k, V v);

    V get(K k);
}

/**
 * 1.1 以K的hashcode为Node数组下标，存储V，即形成了K-V的映射关系
 *
 * @param <K>
 * @param <V>
 */
class HashMap<K, V> implements Map<K, V> {

    private Node<V>[] nodes;

    private int size;

    public HashMap() {
        size = 16;
        nodes = new Node[16];
    }

    static class Node<V> {
        V v;

        public Node(V v) {
            this.v = v;
        }

        public V getV() {
            return v;
        }
    }

    @Override
    public void put(K k, V v) {
        nodes[getHash(k)] = new Node(v);
    }

    @Override
    public V get(K k) {
        return nodes[getHash(k)].getV();
    }

    private int getHash(K k) {
        return k.hashCode();
    }

}
