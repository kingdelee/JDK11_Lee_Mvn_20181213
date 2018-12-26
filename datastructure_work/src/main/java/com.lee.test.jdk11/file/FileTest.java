package com.lee.test.jdk11.file;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

public class FileTest {

    public static void main(String[] args) {

        Stream.of("07-Set-and-Map",
                "08-MaxHeap-and-Priority-Queue",
                "09-Segment-Tree",
                "10-Trie",
                "11-Union-Find",
                "12-AVL-Tree",
                "13-Red-Black-Tree",
                "14-Hash-Table").forEach(j -> {
            String path = "/Volumes/Seagate Backup Plus Drive/IT培训/Alogrithm/Alogrithm/[IT教程吧-www.itjc8.com]_玩转数据结构 从入门到进阶/源码/Play-with-Data-Structures-master/Play-with-Data-Structures-master/" + j;
            File file = new File(path);

            File[] files = file.listFiles();
            Arrays.stream(files).forEach(i -> {

                String name = i.getName();
                char c = name.charAt(0);
                if (c == 48 || c == 49) {
                    name = "t_" + name;
                }
                name = name.replace("-", "_");
                System.out.println(name);
                i.renameTo(new File(path + "/" + name));


            });
        });


    }

}
