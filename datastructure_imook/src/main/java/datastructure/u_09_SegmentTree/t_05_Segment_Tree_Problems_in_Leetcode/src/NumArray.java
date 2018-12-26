package datastructure.u_09_SegmentTree.t_05_Segment_Tree_Problems_in_Leetcode.src;/// 303. Range Sum Query - Immutable
/// https://leetcode.com/problems/range-sum-query-immutable/description/

class NumArray {

    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {

        if (nums.length > 0) {
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++)
                data[i] = nums[i];
            segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
        }

    }

    public int sumRange(int i, int j) {

        if (segmentTree == null)
            throw new IllegalArgumentException("Segment Tree is null");

        return segmentTree.query(i, j);
    }
}
