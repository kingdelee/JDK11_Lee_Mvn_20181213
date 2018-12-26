package com.lee.test.jdk11.java.base.java.util;

import com.lee.test.jdk11.java.base.java.util.test.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HashMapTest {

    static Stream<Person> personStream;
    private static final Logger logger = LogManager.getLogger(HashMapTest.class);


    @Test
    public void t1() {
        HashMap<Person, Person> hashMap = new HashMap<>();
        personStream.forEach(person -> {
            hashMap.put(person, person);
            System.out.println("person:" + person.getName());
        });
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
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(3, 3);
        hashMap.put(4, 4);

//        IntStream.rangeClosed(1,6).forEach(i->hashMap.put(i, i));

        Set<Map.Entry<Integer, Integer>> entries = hashMap.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
            System.out.println("key:" + entry.getKey() + ",val:" + entry.getValue());

        }
    }

    /**
     * 扩容判断
     */
    @Test
    public void t5_2() {
        Stream<Person> personStream = Stream.of(
                new Person("a"),
                new Person("b1"),
                new Person("b2"),
                new Person("b3"),
                new Person("c"),
                new Person("d"),
                new Person("e"));

        Node node = new Node("b0");


        node.setNext(new Node("b1"))
                .setNext(new Node("b2"))
                .setNext(new Node("b3"));
//                .setNext(new Node("c"))
//                .setNext(new Node("d"))
//                .setNext(new Node("e"));
        logger.info(node);

//        node1.setNext(node2);
//        node2.setNext(node3);
//        node3.setNext(node4);
//        node4.setNext(node5);
//        node5.setNext(node6);
//        node6.setNext(node7);
        logger.info(node);

        t5_m1(node);
    }

    @Test
    public void t5_3() {
        Stream<Person> personStream = Stream.of(
                new Person("a"),
                new Person("b1"),
                new Person("b2"),
                new Person("b3"),
                new Person("b4"),
                new Person("c1"),
                new Person("c2"),
                new Person("c3"),
                new Person("c4"),
                new Person("d1"),
                new Person("d2"),
                new Person("d3"),
                new Person("e"));

        HashMap<Person, Person> hashMap = new HashMap<>();
        personStream.forEach(person -> hashMap.put(person, person));

    }

    @Test
    public void t5_3_hash1() {
        IntStream.rangeClosed(1, 33).forEach(
                i -> logger.info("h:{}, index:{}", i, t5_m3(i))
        );
    }

    public int t5_m2(int h) {
        return h ^ (h >>> 16);
    }

    public int t5_m3(int h) {
        return h & (16 - 1);
    }

    @Test
    public void t5_3_hash2() {
        IntStream.rangeClosed(1, 20).forEach(
                i -> logger.info("1-20&16, {}&16={}", i, i & 16)
        );
    }


    public void t5_m1(Node e) { // preserve order

        Node loHead = null, loTail = null;
        Node hiHead = null, hiTail = null;
        Node next;

        int oldCap = 16;
        int j = 0;
        do {
            next = e.next;
            if ((e.hash & oldCap) == 0) {
                logger.info("e:" + e.hash);
                if (loTail == null)
                    loHead = e;
                else
                    loTail.next = e;
                loTail = e;
            } else {
                logger.info("e:" + e.hash);
                if (hiTail == null)
                    hiHead = e;
                else
                    hiTail.next = e;
                hiTail = e;
            }
        } while ((e = next) != null);

        if (loTail != null) {
            loTail.next = null;
            logger.info("newTab[j]:" + j);
//            newTab[j] = loHead;
        }

        if (hiTail != null) {
            hiTail.next = null;
            logger.info("newTab[j + oldCap]:" + j + oldCap);
//            newTab[j + oldCap] = hiHead;
        }

    }

    @Test
    public void t5_4_tableSizeFor() {
        IntStream.rangeClosed(0, 16).forEach(i -> {
            logger.info("i:{}, r:{}", i, tableSizeFor(i));
        });
    }

    @Test
    public void t5_4_2_tableSizeFor() {
        IntStream.rangeClosed(0, 33).forEach(
                i -> logger.info("i:{}, result:{}", i, numberOfLeadingZeros(i))
        );
    }

    @Test
    public void t5_4_3_tableSizeFor() {
        IntStream.rangeClosed(4, 15).forEach(
                i -> logger.info("i:{}, result:{}", i, numberOfLeadingZeros_2(i))
        );
    }

    /**
     * 1 <<< 16, 65536, 15
     * 1 <<< 8, 256,    23
     * 1 <<< 4, 16,     27
     * 1 <<< 2, 4,      29
     */
    @Test
    public void t5_1() {
//        int i = tableSizeFor(17);
        int i = 17;
        int n = 31;
        logger.info("1 << 16:{}, n -= 16:{}, i >>>= 16:{}, i >>> 1:{}", 1 << 16, n - 16, i >>>= 16, i >>> 1);
        logger.info("1 << 8:{}, n -= 8:{}, i >>>= 8:{}, i >>> 1:{}", 1 << 8, n - 8, i >>>= 8, i >>> 1);
        logger.info("1 << 4:{}, n -= 4:{}, i >>>= 4:{}, i >>> 1:{}", 1 << 4, n - 4, i >>>= 4, i >>> 1);
        logger.info("1 << 2:{}, n -= 2:{}, i >>>= 2:{}, i >>> 1:{}", 1 << 2, n - 2, i >>>= 2, i >>> 1);

    }

    @Test
    public void t5_1_1() {
        IntStream.rangeClosed(1, 16).forEach(i -> {
            logger.info("i:{}, i >>> 1:{}", i, i >>> 1);
        });
    }

    @Test
    public void t5_1_2() {
        IntStream.rangeClosed(1, 64).forEach(i -> {
            logger.info("-1 >>> {}:{}", i, -1 >>> i);
        });

        IntStream.rangeClosed(1, 64).forEach(i -> {
            logger.info("1 >>> {}:{}", i, 1 >>> i);
        });
    }

    @Test
    public void t5_1_3() {
        logger.info("cap:{}, n - (i >>> 1):{}, r:{}", 1, 31, -1 >>> 31);
        logger.info("cap:{}, n - (i >>> 1):{}, r:{}", 2, 30, -1 >>> 30);
        logger.info("cap:{}, n - (i >>> 1):{}, r:{}", 4, 29, -1 >>> 29);
        logger.info("cap:{}, n - (i >>> 1):{}, r:{}", 8, 28, -1 >>> 28);
        logger.info(-8 >>> 2);


        logger.info("cap:{}, n - (i >>> 1):{}, r:{}", 8, 28, -1 >>> 28);

    }

    @Test
    public void t5_1_4() {

        int i = tableSizeFor((129));
        logger.info(i);
        logger.info(1 << 16);
    }


    static final int tableSizeFor(int cap) {
//        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        int n = -1 >>> numberOfLeadingZeros(cap - 1);
        return n + 1;
    }

    public static int numberOfLeadingZeros(int i) {
        // HD, Count leading 0's
        logger.info("i:{}", i);
        if (i <= 0) {
            return i == 0 ? 32 : 0;
        }
        int n = 31;
        if (i >= 1 << 16) {
            logger.info("【65536】i >= 1 << 16, i:{}", i);
            n -= 16;
            i >>>= 16;
            logger.info("n:{}, i:{}", n, i);
        }
        if (i >= 1 << 8) {
            logger.info("【256】i >= 1 << 8, i:{}", i);
            n -= 8;
            i >>>= 8;
            logger.info("n:{}, i:{}", n, i);
        }
        if (i >= 1 << 4) {
            logger.info("【16】i >= 1 << 4, i:{}", i);
            n -= 4;
            i >>>= 4;
            logger.info("n:{}, i:{}", n, i);
        }
        if (i >= 1 << 2) {
            logger.info("【4】i >= 1 << 2, i:{}", i);
            n -= 2;
            i >>>= 2;
            logger.info("n:{}, i:{}", n, i);
        }
        logger.info("i >>> 1:{}, n:{}, n - (i >>> 1)", i >>> 1, n, n - (i >>> 1));
        return n - (i >>> 1);
    }

    public static int numberOfLeadingZeros_2(int i) {
        // HD, Count leading 0's
        if (i <= 0) {
            return i == 0 ? 32 : 0;
        }
        int n = 31;
        if (i >= 1 << 16) {
            n -= 16;
            i >>>= 16;
        }
        if (i >= 1 << 8) {
            n -= 8;
            i >>>= 8;
        }
        if (i >= 1 << 4) {
            n -= 4;
            i >>>= 4;
        }
        if (i >= 1 << 2) {
            n -= 2;
            i >>>= 2;
        }
        return n - (i >>> 1);
    }

    public static int numberOfLeadingZeros2(int i) {
        //
        if (i == 0)    //步骤一
            return 32;
        int n = 1;
        //步骤二
        if (i >>> 16 == 0) {
            n += 16;
            i <<= 16;
        }//分析一
        if (i >>> 24 == 0) {
            n += 8;
            i <<= 8;
        }
        if (i >>> 28 == 0) {
            n += 4;
            i <<= 4;
        }
        if (i >>> 30 == 0) {
            n += 2;
            i <<= 2;
        }
        n -= i >>> 31;//分析二
        return n;
    }


    class Node {
        Node next;
        int hash;
        String key;

        public Node(String key) {
            this.key = key;
            setHash();
        }

        public Node setNext(Node next) {
            this.next = next;
            return next;
        }

        public void setHash() {
            int h;
            this.hash = (key == null) ? 0 : (h = hashCode()) ^ (h >>> 16);
            logger.info(hash);
        }

        public String getKey() {
            return key;
        }

        @Override
        public int hashCode() {
            int hashcode = super.hashCode();
            if (key.startsWith("a")) {
                hashcode = 2;
            } else if (key.startsWith("b")) {
                hashcode = 3;
            } else if (key.startsWith("c")) {
                hashcode = 4;
            }
            return hashcode;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Person) {
                String name = ((Node) obj).getKey();
                if (this.getKey().equals(name)) {
                    return true;
                }
            }
            return super.equals(obj);
        }

    }

}
