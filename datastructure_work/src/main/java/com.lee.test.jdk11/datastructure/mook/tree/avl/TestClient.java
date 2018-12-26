package com.lee.test.jdk11.datastructure.mook.tree.avl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TestClient {

    private static final Logger logger = LogManager.getLogger(AVLTree2Log.class);


    @Test
    public void t1_height() {
        AVLTree avlTree = new AVLTree();

        avlTree.put(100, 100);
        avlTree.put(50, 50);
        avlTree.put(120, 120);
        avlTree.put(40, 40);
        avlTree.put(60, 60);
        avlTree.put(20, 20);
        avlTree.put(45, 45);

        System.out.println(avlTree.getAnyNodeHeight(50));


    }

    @Test
    public void del() {
        AVLTree2Log avlTree = new AVLTree2Log();

        avlTree.put(100, 100);
        avlTree.put(50, 50);
        avlTree.put(140, 140);
        avlTree.put(145, 145);
        avlTree.put(40, 40);
        avlTree.put(150, 150);
        avlTree.put(70, 70);
        avlTree.put(130, 130);
        avlTree.put(20, 20);
        avlTree.put(125, 125);
        avlTree.put(135, 135);
        avlTree.put(147, 147);
        avlTree.put(160, 160);

//        Object[] arrByBFS = avlTree.getArrByBFS();
//
//        for (Object arrByBF : arrByBFS) {
//            System.out.println((int)arrByBF);
//        }

//        System.out.println(avlTree.getAnyNodeHeight(50));

        avlTree.remove(145);


        StringBuilder sb = new StringBuilder();
        Arrays.asList(avlTree.getArrByBFS()).forEach(i -> sb.append(i + ","));
        logger.info("-----sb:" + sb.toString());

    }

}
