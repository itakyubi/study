package com.wa.leetcode;

public class NumArray {
    private int[] tree;
    private int len;

    public NumArray(int[] nums) {
        if (nums != null && nums.length > 0) {
            len = nums.length;
            tree = new int[len * 2];
            buildTree(nums);
        }
    }

    public void update(int i, int val) {
        i += len;
        tree[i] = val;
        while (i > 0) {
            int left = i;
            int right = i;
            if ((i & 1) == 0) {
                right = i + 1;
            } else {
                left = i - 1;
            }
            tree[i / 2] = tree[left] + tree[right];
            i /= 2;
        }
    }

    public int sumRange(int i, int j) {
        i += len;
        j += len;
        int sum = 0;
        while (i <= j) {
            if ((i & 1) == 1)
                sum += tree[i++];
            if ((j & 1) == 0)
                sum += tree[j--];
            i /= 2;
            j /= 2;
        }
        return sum;
    }

    private void buildTree(int[] nums) {
        for (int i = len, j = 0; i < 2 * len; i++, j++)
            tree[i] = nums[j];
        for (int i = len - 1; i > 0; i--)
            tree[i] = tree[2 * i] + tree[2 * i + 1];
    }
}
