package com.lee.test.jdk11.datastructure.mook.linkedlist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class LinkedListTest {

    private static final Logger logger = LogManager.getLogger(LinkedListTest.class);

    LinkedList<Integer> list = new LinkedList();

    @Test
    public void t1() {
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        logger.info(list.toString());


    }

    @Test
    public void t2() {
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();

        logger.info(list.toString());

    }

    @Test
    public void t3() {
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        list.addFirst(6);

//        for (Integer integer : list) {
//            System.out.println(integer);
//        }

        ListIterator<Integer> iterator = (ListIterator<Integer>) list.iterator();

        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.prev());
        System.out.println(iterator.prev());
        System.out.println(iterator.next());
        System.out.println(iterator.prev());
        System.out.println(iterator.next());

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }


}
