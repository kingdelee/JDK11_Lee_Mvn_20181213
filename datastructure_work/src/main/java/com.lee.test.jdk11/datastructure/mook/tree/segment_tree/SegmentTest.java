package com.lee.test.jdk11.datastructure.mook.tree.segment_tree;

import org.junit.jupiter.api.Test;

public class SegmentTest {

    @Test
    public void t1() {
        Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};


        SegmentTree<Integer> segTree = new SegmentTree<>(nums,
                (a, b) -> a + b);

        System.out.println(segTree);
        System.out.println(segTree.queryByMerge(2, 3));
    }

}
