package com.wa.leetcode;

/**
 * BestRotation
 * https://leetcode-cn.com/problems/smallest-rotation-with-highest-score/
 * 2022-03-09 09:09
 *
 * @author wuao
 */
public class BestRotation {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 4, 0};
        int[] nums2 = new int[]{1, 3, 0, 2, 4};

        System.out.println(bestRotation(nums));
        System.out.println(bestRotation(nums2));
    }

    /**
     * https://leetcode-cn.com/problems/smallest-rotation-with-highest-score/solution/chai-fen-shu-zu-qu-jian-geng-xin-by-wen-0uey1/
     */
    private static int bestRotation(int[] nums) {
        int n = nums.length;

        // diff[i]代表移动i次时，可以获得积分的次数
        // diff[i]在开始获得积分时+1，在开始不获得积分时-1
        // 之所以使用差分数组，是因为不需要每次都真的更新一个完整的区间，只需要在区间开头加1，在区间末尾之后减1就可以了
        // 最后使用前缀和即可求出每一点的实际值
        int[] diff = new int[n + 1];
        for (int i = 0; i < n; i++) {
            // 对于nums[i]来说，i在[nums[i], n-1]范围内才可以获得积分
            if (i < nums[i]) {
                // 当i<nums[i]时，说明至少要把nums[i]移动到n-1的位置才能开始获得积分
                diff[i + 1]++; // 移动到n-1位置时，才可以获得积分，此时移动了i+1次
                diff[n - (nums[i] - i) + 1]--; // 移动到nums[i]-1位置时，就开始不能获得积分了，此时移动了n-(nums[i]-i)+1次
            } else {
                // 当i>nums[i]时，说明此时就可以获得积分，并且随着移动次数增大，是先获得积分，然后不能获得积分，然后又能获得积分
                diff[0]++; // 不移动即可获得积分
                diff[i - nums[i] + 1]--; // 当移动到nums[i]-1位置时，开始不能获得积分，此时移动了i-nums[i]+1次
                diff[i + 1]++; // 当移动到n-1位置时，又可以获得积分，此时移动了i+1次
            }
        }

        int max = 0, sum = 0, k = 0;
        for (int i = 0; i < n; i++) {
            sum += diff[i];
            if (sum > max) {
                max = sum;
                k = i;
            }
        }
        return k;
    }


    // 暴力法超时
    private static int bestRotation2(int[] nums) {
        int[] scores = new int[nums.length]; // score[i]代表轮调i时的分值
        for (int i = 0; i < nums.length; i++) {
            for (int k = 0; k < nums.length; k++) {
                if (i - k < 0) {
                    if (nums[i] <= nums.length - (k - i)) {
                        scores[k]++;
                    }
                } else {
                    if (nums[i] <= i - k) {
                        scores[k]++;
                    }
                }
            }
        }

        int k = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
                k = i;
            }
        }
        return k;
    }

}
