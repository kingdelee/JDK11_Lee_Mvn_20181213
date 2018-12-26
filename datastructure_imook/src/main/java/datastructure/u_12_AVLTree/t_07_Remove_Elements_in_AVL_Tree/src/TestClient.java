package datastructure.u_12_AVLTree.t_07_Remove_Elements_in_AVL_Tree.src;

import org.junit.jupiter.api.Test;

public class TestClient {

    @Test
    public void t1_height() {
        AVLTree avlTree = new AVLTree();

        avlTree.add(100, 100);
        avlTree.add(50, 50);
        avlTree.add(120, 120);
        avlTree.add(40, 40);
        avlTree.add(60, 60);
        avlTree.add(20, 20);
        avlTree.add(45, 45);


    }

    @Test
    public void del() {
        AVLTree avlTree = new AVLTree();

        avlTree.add(100, 100);
        avlTree.add(50, 50);
        avlTree.add(140, 140);
        avlTree.add(145, 145);
        avlTree.add(40, 40);
        avlTree.add(150, 150);
        avlTree.add(70, 70);
        avlTree.add(130, 130);
        avlTree.add(20, 20);
        avlTree.add(125, 125);
        avlTree.add(135, 135);
        avlTree.add(147, 147);
        avlTree.add(160, 160);

//        Object[] arrByBFS = avlTree.getArrByBFS();
//
//        for (Object arrByBF : arrByBFS) {
//            System.out.println((int)arrByBF);
//        }

//        System.out.println(avlTree.getAnyNodeHeight(50));


    }

}
