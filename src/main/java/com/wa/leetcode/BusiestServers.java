package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * BusiestServers
 * https://leetcode-cn.com/problems/find-servers-that-handled-most-number-of-requests/
 * 2022-03-30 09:17
 *
 * @author wuao
 */
public class BusiestServers {

    public static void main(String[] args) {
        int k = 3;
        int[] arrival = new int[]{1, 2, 3, 4, 5}, load = new int[]{5, 2, 3, 3, 3};

        int k2 = 3;
        int[] arrival2 = new int[]{1, 2, 3, 4}, load2 = new int[]{1, 2, 1, 2};

        int k3 = 3;
        int[] arrival3 = new int[]{1, 2, 3}, load3 = new int[]{10, 12, 11};

        int k4 = 3;
        int[] arrival4 = new int[]{1, 2, 3, 4, 8, 9, 10}, load4 = new int[]{5, 2, 10, 3, 1, 2, 2};

        int k5 = 1;
        int[] arrival5 = new int[]{1}, load5 = new int[]{1};

        int k6 = 7;
        int[] arrival6 = new int[]{1, 3, 4, 5, 6, 11, 12, 13, 15, 19, 20, 21, 23, 25, 31, 32}, load6 = new int[]{9, 16, 14, 1, 5, 15, 6, 10, 1, 1, 7, 5, 11, 4, 4, 6};

        System.out.println(busiestServers(k, arrival, load));
        System.out.println(busiestServers(k2, arrival2, load2));
        System.out.println(busiestServers(k3, arrival3, load3));
        System.out.println(busiestServers(k4, arrival4, load4));
        System.out.println(busiestServers(k5, arrival5, load5));
        System.out.println(busiestServers(k6, arrival6, load6));
    }

    private static List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] endTime = new int[k]; // 记录每个服务器当前任务的结束时间，0表示当前空闲
        int[] taskCount = new int[k]; // 记录每个服务器执行任务的数量

        for (int i = 0; i < arrival.length; i++) {
            int startIndex = i % k; // 由第i%k个服务器处理当前任务
            int index = startIndex;

            // 从i%k开始找第一个空闲的服务器
            while (index < k && endTime[index] > arrival[i]) {
                index++;
            }
            // 在[i%k,k]部分找到了空闲服务器
            if (index < k) {
                endTime[index] = arrival[i] + load[i];
                taskCount[index]++;
                continue;
            }

            // 遍历到最后一个还没找到空闲服务器则跳到第一个开始找
            index = 0;
            while (index < startIndex && endTime[index] > arrival[i]) {
                index++;
            }
            if (index < startIndex) {
                endTime[index] = arrival[i] + load[i];
                taskCount[index]++;
            }
        }

        List<Integer> res = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < k; i++) {
            if (taskCount[i] > max) {
                res.clear();
                res.add(i);
                max = taskCount[i];
            } else if (taskCount[i] == max) {
                res.add(i);
            }
        }
        return res;
    }
}
