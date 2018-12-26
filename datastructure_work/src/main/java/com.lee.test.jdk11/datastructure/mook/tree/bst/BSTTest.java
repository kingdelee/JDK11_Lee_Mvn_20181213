package com.lee.test.jdk11.datastructure.mook.tree.bst;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BSTTest {

    @Test
    public void t1() {
        BST<Integer> bst = new BST();
        bst.addRoot(1);
        bst.addVal(2);
        bst.addVal(3);
        bst.addVal(4);
        bst.addVal(5);

        System.out.println(bst.toString());
    }

    @Test
    public void t2_addValRC2() {
        BST<Integer> bst = new BST();
        bst.addRoot(0);

        long startTime = System.nanoTime();

        IntStream.range(1, 60_000).forEach(i -> bst.addVal(i));

        long endTime = System.nanoTime();

        System.out.println((endTime - startTime) / 1000000000.0);

        // 0.645686672
        // 0.620700211

    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST();
        bst.addRoot(1);

        long startTime = System.nanoTime();

        IntStream.range(1, 60_000).forEach(i -> bst.addVal(i));

        long endTime = System.nanoTime();

        System.out.println((endTime - startTime) / 1000000000.0);

        // 12.710324088
        // 12.205063359
        // 12.361334659
        // 12.212599065
        // 12.339038919

        // 9.38226848
        // 9.057065519
        // 8.917971391
        // 9.054027079
        // 9.175964054

        // 9.157076795
        // 9.109742262
        // 9.354920937
        // 9.056713725
        // 9.108316693

        // 9.177820647
        // 9.30995701
        // 9.053598829
        // 9.212024205
        // 9.062174262


    }

//    private static int i = 0;
//    public static void a(){
//        System.out.println(i++);
//        a();
//    }
//    public static void main(String[] args) {
//        a();
//    }

    @Test
    public void t3() {
        BST<Integer> bst = new BST();
        bst.addRoot(1);
        bst.addVal(2);
        bst.addVal(3);
        bst.addVal(4);
        bst.addVal(5);

//        System.out.println(bst.remove(4));
        System.out.println(bst.contains(4));
        System.out.println(bst.contains(3));

        Integer integer = bst.get(2);


        System.out.println(integer);
    }

    @Test
    public void t4() {
        T1 t1 = new T1(1);

        m1(t1);
        System.out.println(t1.getI());

    }

    @Test
    public void t5() {
        BST<Integer> bst = new BST();
        bst.addRoot(10);
        bst.addVal(5);
        bst.addVal(15);
        bst.addVal(6);
        bst.addVal(28);
//        bst.addVal(30);
        bst.addVal(20);
        bst.addVal(14);

        Object[] arr = bst.getArrByPreOrder();
        Arrays.asList(arr).forEach(i -> System.out.println(i));


        arr = bst.getArrByBFS();
        Arrays.asList(arr).forEach(i -> System.out.println(i));

        bst.removeMax();

    }

    @Test
    public void t6() {
        BST<Integer> bst = new BST();
        bst.addRoot(10);
        bst.addVal(5);
        bst.addVal(15);
        bst.addVal(6);
        bst.addVal(28);
//        bst.addVal(30);
        bst.addVal(20);
        bst.addVal(14);


        Object[] arr = bst.getArrByBFS();
        Arrays.asList(arr).forEach(i -> System.out.println(i));


        System.out.println();
        bst.removeNoRet(15);
        System.out.println();
        arr = bst.getArrByBFS();
        Arrays.asList(arr).forEach(i -> System.out.println(i));


        bst.removeMax();

    }

    public void m1(T1 t1) {
        t1.setI(2);
        t1 = null;
    }

}

class T1 {
    int i;

    public T1(int i) {
        this.i = i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
