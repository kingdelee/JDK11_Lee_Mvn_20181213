/**
 * Welcome to https://waylau.com
 */
package com.waylau.java.jdk9;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JDK9:List factory.
 *
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2019年1月2日
 */
public class ListFactoryDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // Java 9之前
        List<String> friends = new ArrayList<>();
        friends.add("Alice");
        friends.add("Bob");
        friends.add("Cavin");

        // Java 9之前
        List<String> friends2 = Arrays.asList("Alice", "Bob", "Cavin");

        // Java 9之后
        List<String> friends3 = List.of("Alice", "Bob", "Cavin");
    }

    @Test
    public void testRemoveIf() {
        List<String> friends = new ArrayList<>();
        friends.add("Alice");
        friends.add("Bob");
        friends.add("Cavin");
        friends.add("David");
        friends.add("Eric");
        friends.add("Franck");

		/*
		 // 下面方式会报越界 int size = friends.size();
		 
		 for (var i = 0; i < size; i ++) { if (friends.get(i).contains("A")) {
		 friends.remove(i); } }
		 */
		
		
		/*
		for (Iterator<String> iterator = friends.iterator(); iterator.hasNext();) {
			String friend = iterator.next();
			if (friend.contains("A")) {
				iterator.remove();
			}
		}
		*/

        friends.removeIf(x -> x.contains("A"));

        friends.stream().forEach(System.out::println);
    }


    @Test
    public void testRemoveAll() {
        List<String> friends = new ArrayList<>();
        friends.add("Alice");
        friends.add("Bob");
        friends.add("Cavin");
        friends.add("David");
        friends.add("Eric");
        friends.add("Franck");

        List<String> friends2 = new ArrayList<>();
        friends2.add("Alice");
        friends2.add("Bob");
        friends2.add("Cavin");

        friends.removeAll(friends2);

        friends.stream().forEach(System.out::println);
    }
}
