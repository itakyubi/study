package com.wa.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * AsteroidCollision
 * https://leetcode.cn/problems/asteroid-collision/
 * 735. 行星碰撞
 * 2022-07-13 18:17
 *
 * @author wuao
 */
public class AsteroidCollision {

    /*
    给定一个整数数组 asteroids，表示在同一行的行星。
    对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
    找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。

    示例 1：
        输入：asteroids = [5,10,-5]
        输出：[5,10]
        解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
    示例 2：
        输入：asteroids = [8,-8]
        输出：[]
        解释：8 和 -8 碰撞后，两者都发生爆炸。
    示例 3：
        输入：asteroids = [10,2,-5]
        输出：[10]
        解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
    提示：
        2 <= asteroids.length <= 104
       -1000 <= asteroids[i] <= 1000
        asteroids[i] != 0
    */

    public static void main(String[] args) {
        int[] asteroids = new int[]{5, 10, -5};
        int[] asteroids2 = new int[]{8, -8};
        int[] asteroids3 = new int[]{10, 2, -5};
        int[] asteroids4 = new int[]{-2, -2, 1, -2};

        System.out.println(Arrays.toString(asteroidCollision(asteroids)));
        System.out.println(Arrays.toString(asteroidCollision(asteroids2)));
        System.out.println(Arrays.toString(asteroidCollision(asteroids3)));
        System.out.println(Arrays.toString(asteroidCollision(asteroids4)));
    }

    private static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int asteroid : asteroids) {
            if (stack.isEmpty() || stack.peek() * asteroid > 0 || stack.peek() < 0) {
                stack.push(asteroid);
            } else {
                int val = asteroid;
                while (!stack.isEmpty() && stack.peek() > 0) {
                    if (Math.abs(asteroid) < stack.peek()) {
                        val = 0;
                        break;
                    } else if (Math.abs(asteroid) > stack.peek()) {
                        stack.pop();
                    } else {
                        val = 0;
                        stack.pop();
                        break;
                    }
                }
                if (val != 0) {
                    stack.push(val);
                }
            }
        }

        int[] res = new int[stack.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
