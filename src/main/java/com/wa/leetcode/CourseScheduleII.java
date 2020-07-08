package com.wa.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];

        List<List<Integer>> adjacency = new ArrayList<>();
        int[] indegrees = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        for (int[] p : prerequisites) {
            indegrees[p[0]]++;
            adjacency.get(p[1]).add(p[0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res[count] = cur;
            count++;
            for (int next : adjacency.get(cur)) {
                indegrees[next]--;
                if (indegrees[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return count == numCourses ? res : new int[0];
    }
}
