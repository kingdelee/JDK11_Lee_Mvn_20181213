package com.lee.test.jdk11.jvm.map.t4;

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

    /**
     * 初始化包含hashcode冲突的对象
     */
    @BeforeAll
    public static void t4_data() {
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
    }

    /**
     * 完成迭代器功能
     */
    @Test
    public void t3_2() {

        HashMap.Node[] nodes = new HashMap.Node[3];
        nodes[0] = new HashMap.Node(0, 0);
        nodes[1] = new HashMap.Node(1, 1);
        nodes[2] = new HashMap.Node(2, 2);
        HashMap.HashMapIterator hashMapIterator = new HashMap.HashMapIterator(nodes);
        while (hashMapIterator.hasNext()) {
            HashMap.Node next = hashMapIterator.next();
            System.out.println(next.getKey());
        }
    }

    /**
     * 目标：解决hashcode冲突问题
     * 方案：使用链表的形式将冲突的元素通过链表存储
     * 案例：personStream，定义了a开头的name的hashcode是一样的
     * 期望结果：能够迭代输出所有的元素
     */
    @Test
    public void t4_error() {
        HashMap<Person, Person> hashMap = new HashMap<>();
        personStream.forEach(person -> hashMap.put(person, person));

    }


    @Test
    public void t4_error2() {
        HashMap<Person, Person> hashMap = new HashMap<>();
        personStream.forEach(person -> hashMap.put(person, person));

        Set<Map.Entry<Person, Person>> entries = hashMap.entrySet();
        for (Map.Entry<Person, Person> entry : entries) {
            Person key = entry.getKey();
            Person value = entry.getValue();
            System.out.println("key:" + key.getName() + ",val:" + entry.getValue().getName());
        }
    }

    @Test
    public void t4_error3() {
        HashMap<Person, Person> hashMap = new HashMap<>();
        personStream.forEach(person -> {
            hashMap.put(person, person);
            System.out.println("person:" + person.getName()
                    + "," + hashMap.getArrIndex(person));
        });


    }


}

interface Map<K, V> {
    void put(K k, V v);

    V get(K k);

    Set<Entry<K, V>> entrySet();

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

interface Set<E> extends Iterable<E> {

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
 * 1.3.2 目标：完成迭代器功能
 * 1.4   目标：解决hashcode冲突问题
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
    public Set<Entry<K, V>> entrySet() {

        return new Set<Entry<K, V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new HashMapIterator(nodes);
            }
        };

    }


    static class Node<K, V> implements Entry<K, V> {
        Node next;
        K k;
        V v;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }


        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }

    /**
     * 目标：在这里通过通过链表的形式解决hashcode冲突
     * 实现：get出来，判断是否存在有值，如果有值再判断对象是否一致，不一致则通过链表方式插入，即当前节点引用下一个节点
     *
     * @param k
     * @param v
     */
    @Override
    public void put(K k, V v) {

        Node<K, V> currentNode = nodes[getArrIndex(k)];
        Node nextNode = new Node(k, v);
        if (currentNode == null) {
            nodes[getArrIndex(k)] = nextNode;
        } else if (currentNode == nextNode || currentNode.equals(nextNode) || k == null) {
            return;
        } else {
            putInLinkedList(currentNode, nextNode);
        }

    }

    /**
     * 通过链表的形式加进来，那么则需要找到root节点的最后一个节点，在后面追加
     *
     * @param currentNode
     * @param nextNode
     */
    private void putInLinkedList(Node<K, V> currentNode, Node nextNode) {
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.setNext(nextNode);
    }

    @Override
    public V get(K k) {
        return nodes[getArrIndex(k)].getValue();
    }

    int getArrIndex(K k) {
        return k.hashCode() & (size - 1);
    }

    static class HashMapIterator<K, V> implements Iterator<Entry<K, V>> {

        Node<K, V> next;
        Node<K, V> current;
        int index;
        Node[] nodes;
        int len;

        /**
         * 1.通过构造函数去注入需要迭代的对象
         * 2.因为迭代的数组是有空缺的，所以必须足一判断内容元素是否空
         * 3.在构造函数中，迭代数组，返回第一个节点
         * 4.注意索引值要在长度范围内
         */
        public HashMapIterator(Node[] nodes) {
            this.nodes = nodes;
            len = nodes.length;
            // loop nodes in i
            // [i] != null -> next
//            while(index < len && (next = nodes[index++]) == null){
//
//            }

        }

        /**
         * 设计：判断下一个节点是否存在，不会改变下标等参数;
         * 执行多次结果一致
         *
         * @return
         */
        @Override
        public boolean hasNext() {

            int tmpIndex = index;
            while (tmpIndex < len && (next = nodes[tmpIndex++]) == null) {

            }

            return next != null;
        }


        /**
         * 设计：如果next存在，则输出，并将下标移动；如果不存在，则输出null
         * 思路：如果next节点存在则返回，否则迭代查找
         *
         * @return
         */
        @Override
        public Node<K, V> next() {
            current = next;
            while (index < len && (next = nodes[index++]) == null) {
            }
            return current;
        }


    }

}
