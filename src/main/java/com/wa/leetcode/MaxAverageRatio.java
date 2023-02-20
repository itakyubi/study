package com.wa.leetcode;

import java.util.PriorityQueue;

/**
 * MaxAverageRatio
 * https://leetcode.cn/problems/maximum-average-pass-ratio/
 * 1792. 最大平均通过率
 * 2023/2/20 7:06 下午
 *
 * @author wuao
 */
public class MaxAverageRatio {

   /* 
    一所学校里有一些班级，每个班级里有一些学生，现在每个班都会进行一场期末考试。给你一个二维数组 classes，其中classes[i] = [passi, totali]，表示你提前知道了第i个班级总共有totali个学生，其中只有passi个学生可以通过考试。
    给你一个整数extraStudents，表示额外有extraStudents个聪明的学生，他们 一定能通过任何班级的期末考。你需要给这extraStudents个学生每人都安排一个班级，使得 所有班级的 平均通过率 最大。
    一个班级的通过率等于这个班级通过考试的学生人数除以这个班级的总人数。平均通过率是所有班级的通过率之和除以班级数目。
    请你返回在安排这 extraStudents 个学生去对应班级后的 最大平均通过率。与标准答案误差范围在10-5以内的结果都会视为正确结果。

    示例 1：
        输入：classes = [[1,2],[3,5],[2,2]], extraStudents = 2
        输出：0.78333
        解释：你可以将额外的两个学生都安排到第一个班级，平均通过率为 (3/4 + 3/5 + 2/2) / 3 = 0.78333 。
    示例 2：
        输入：classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
        输出：0.53485

    提示：
        1 <= classes.length <= 10^5
        classes[i].length == 2
        1 <= passi <= totali <= 10^5
        1 <= extraStudents <= 10^5
    */

    public static void main(String[] args) {
        int[][] classes = new int[][]{{1, 2}, {3, 5}, {2, 2}};
        int extra = 2;

        int[][] classes2 = new int[][]{{2, 4}, {3, 9}, {4, 5}, {2, 10}};
        int extra2 = 4;

        System.out.println(maxAverageRatio(classes, extra));
        System.out.println(maxAverageRatio(classes2, extra2));
    }

    private static double maxAverageRatio(int[][] classes, int extraStudents) {
        // 按照增加一个人后通过率的提升幅度构建堆
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(classes.length, (o1, o2) -> {
            double a = ((double) (o1[0] + 1) / (double) (o1[1] + 1)) - ((double) o1[0] / (double) o1[1]);
            double b = ((double) (o2[0] + 1) / (double) (o2[1] + 1)) - ((double) o2[0] / (double) o2[1]);
            if (a == b) {
                return 0;
            }
            return a > b ? -1 : 1;
        });
        for (int[] cla : classes) {
            minHeap.offer(new int[]{cla[0], cla[1]});
        }

        // 遍历堆，加入新同学
        int hundred = 0;
        // 有可能存在所有班级都是100%通过率的情况
        while (extraStudents > 0 && !minHeap.isEmpty()) {
            int[] cla = minHeap.poll();
            int a = cla[0], b = cla[1];
            if (a == b) {
                hundred++;
            } else {
                minHeap.add(new int[]{a + 1, b + 1});
                extraStudents--;
            }
        }

        // 计算最终的通过率
        double sum = 0;
        while (!minHeap.isEmpty()) {
            int[] cla = minHeap.poll();
            sum += (double) cla[0] / cla[1];
        }

        return (sum + hundred) / classes.length;
    }
}
