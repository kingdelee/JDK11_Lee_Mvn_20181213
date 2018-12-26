package com.lee.test.jdk11.jvm.map.t3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class HashMapTest1 {

    static Stream<Person> personStream;

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
     * 通过 1.2的修改，结果能够正常输出
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

    /**
     * 新增需求：添加遍历hashMap的方法
     */
    @Test
    public void t2_1() {

        HashMap<String, String> hashMap = new HashMap<>();


    }

    /**
     * 问题：当hashcode相同时，会发生hash冲突前一个会覆盖后一个
     * 需要：需要使用链表方式让冲突的hashcode通过链表形式逐一追加
     */
    @Test
    public void t3() {

        HashMap<String, String> hashMap = new HashMap<>();

//        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
////        for (Map.Entry<String, String> entry : entries) {
////            entry.getKey();
////            entry.getValue();
////        }

    }

    @BeforeAll
    public static void t3_data() {
        personStream = Stream.of(new Person("a1"),
                new Person("a2"),
                new Person("a3"),
                new Person("b"),
                new Person("c"),
                new Person("d"),
                new Person("e"));

    }

    static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public int hashCode() {
            int hashcode = super.hashCode();
            if (name.startsWith("a")) {
                hashcode = 10000;
            } else if (name.startsWith("b")) {
                hashcode = 20000;
            } else if (name.startsWith("c")) {
                hashcode = 30000;
            }
            return hashcode;
        }
    }

    /**
     * 完成迭代器功能
     */
    @Test
    public void t4() {

        HashMap.Node[] nodes = new HashMap.Node[3];
        nodes[0] = new HashMap.Node(0, 0);
        nodes[1] = new HashMap.Node(1, 1);
        nodes[2] = new HashMap.Node(2, 2);
        HashMap.HashMapIterator hashMapIterator = new HashMap.HashMapIterator(nodes);
        while (hashMapIterator.hasNext()) {
            HashMap.Node next = hashMapIterator.next();
            System.out.println(next.getK());
        }
    }

}

interface Map<K, V> {
    void put(K k, V v);

    V get(K k);

    Entry<K, V> entrySet();
//    Set<Map.Entry<K, V>> entrySet();

    interface Entry<K, V> {
        K getKey();

        V getValue();
    }

    default void foreach(BiConsumer<K, V> action) {
//        for (Entry<K, V> entry : entrySet()) {
//            action.accept(entry.getKey(), entry.getValue());
//        }

//        for (Entry<K, V> entry : entrySet()) {
//            action.accept(entry.getKey(), entry.getValue());
//        }

    }

}

/**
 * 1.1 以K的hashcode为Node数组下标，存储V，即形成了K-V的映射关系
 * 1.2 目标：解决hashcode算法问题，让hashcode的值散列在数组容量内
 * 数学：引用原理，当容量值为2的n次幂时，2的n次幂-1 二进制恰好是000...1111
 * 与0&都是0限制了结果一定是在容量内，与1&保留了原有的hash属性
 * 结论： hashcode & (size-1)
 * 1.2.1 目标：新增遍历hashmap的方法
 * 发现并没有存储key，在遍历的时候会丢失，所以在node中存储key
 * 1.3.1 目标：使容量可以自定义
 * 1.3.2 目标：使容量随着put可以动态扩展
 *
 * @param <K>
 * @param <V>
 */
class HashMap<K, V> implements Map<K, V> {

    private Node<K, V>[] nodes;

    private int size = 16;

    public HashMap() {
        nodes = new Node[size];
    }

    public HashMap(int size) {
        this.size = size;
        nodes = new Node[size];
    }

    @Override
    public Entry<K, V> entrySet() {


        class Entry<K, V> implements Map.Entry<K, V> {

            @Override
            public K getKey() {
                return null;
            }

            @Override
            public V getValue() {
                return null;
            }

        }

        return null;
    }


    static class Node<K, V> {
        K k;
        V v;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }

        public V getV() {
            return v;
        }

        public K getK() {
            return k;
        }

    }

    @Override
    public void put(K k, V v) {
        nodes[getArrIndex(k)] = new Node(k, v);
    }

    @Override
    public V get(K k) {
        return nodes[getArrIndex(k)].getV();
    }


    private int getArrIndex(K k) {
        return k.hashCode() & (size - 1);
    }

    static class HashMapIterator<K, V> implements Iterator<Node<K, V>> {

        Node<K, V> next;
        Node<K, V> current;
        int index;
        Node[] nodes;
        int len;

        /**
         * 1.通过构造函数去注入需要迭代的对象
         * 2.因为迭代的是数组，所以
         */
        public HashMapIterator(Node[] nodes) {
            this.nodes = nodes;
            len = nodes.length;
            if (index < len) {
                next = nodes[index + 1];
            }
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }


        /**
         * 只有在hasNext成立的条件下，才能执行next方法，输出当前值，并将下标后移1位
         * 思路：1.遍历数组，当下一个存在的时候，就返回
         * 2.因为是迭代器，所以需要保存当前下标索引
         * 3.要将next保存，以便hasNext判断
         *
         * @return
         */
        @Override
        public Node<K, V> next() {
            if (index < len) {
                current = nodes[index];
                if (++index < len) {
                    next = nodes[index];
                } else {
                    next = null;
                }
                return current;
            } else {
                throw new RuntimeException();
            }
        }


    }

}
