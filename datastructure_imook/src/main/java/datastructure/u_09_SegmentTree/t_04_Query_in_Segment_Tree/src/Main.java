package datastructure.u_09_SegmentTree.t_04_Query_in_Segment_Tree.src;

import org.junit.jupiter.api.Test;

/// 该测试用例来源：Leetcode 303. Range Sum Query - Immutable
/// https://leetcode.com/problems/range-sum-query-immutable/description/
public class Main {

    public static void main(String[] args) {

        Integer[] nums = {-2, 0, 3, -5, 2, -1};

        SegmentTree<Integer> segTree = new SegmentTree<>(nums,
                (a, b) -> a + b);
        System.out.println(segTree);

        System.out.println(segTree.query(0, 2));
        System.out.println(segTree.query(2, 5));
        System.out.println(segTree.query(0, 5));
    }

    @Test
    public void t1() {

        Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};


        SegmentTree<Integer> segTree = new SegmentTree<>(nums,
                (a, b) -> a + b);

        System.out.println(segTree);
        System.out.println(segTree.query(2, 3));
        System.out.println(segTree.query(2, 3));
        System.out.println(segTree.query(2, 3));


    }

}
