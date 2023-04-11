package com.wa.leetcode;

/**
 * IsRobotBounded
 * https://leetcode.cn/problems/robot-bounded-in-circle/
 * 1041. 困于环中的机器人
 * 2023/4/11 9:31 上午
 *
 * @author wuao
 */
public class IsRobotBounded {

    /*
    在无限的平面上，机器人最初位于(0, 0)处，面朝北方。注意:
    北方向 是y轴的正方向。
    南方向 是y轴的负方向。
    东方向 是x轴的正方向。
    西方向 是x轴的负方向。
    机器人可以接受下列三条指令之一：
        "G"：直走 1 个单位
        "L"：左转 90 度
        "R"：右转 90 度
    机器人按顺序执行指令instructions，并一直重复它们。
    只有在平面中存在环使得机器人永远无法离开时，返回true。否则，返回 false。

    示例 1：
        输入：instructions = "GGLLGG"
        输出：true
        解释：机器人最初在(0,0)处，面向北方。
            “G”:移动一步。位置:(0,1)方向:北。
            “G”:移动一步。位置:(0,2).方向:北。
            “L”:逆时针旋转90度。位置:(0,2).方向:西。
            “L”:逆时针旋转90度。位置:(0,2)方向:南。
            “G”:移动一步。位置:(0,1)方向:南。
            “G”:移动一步。位置:(0,0)方向:南。
            重复指令，机器人进入循环:(0,0)——>(0,1)——>(0,2)——>(0,1)——>(0,0)。
            在此基础上，我们返回true。
    示例 2：
        输入：instructions = "GG"
        输出：false
        解释：机器人最初在(0,0)处，面向北方。
            “G”:移动一步。位置:(0,1)方向:北。
            “G”:移动一步。位置:(0,2).方向:北。
            重复这些指示，继续朝北前进，不会进入循环。
            在此基础上，返回false。
    示例 3：
        输入：instructions = "GL"
        输出：true
        解释：机器人最初在(0,0)处，面向北方。
            “G”:移动一步。位置:(0,1)方向:北。
            “L”:逆时针旋转90度。位置:(0,1).方向:西。
            “G”:移动一步。位置:(- 1,1)方向:西。
            “L”:逆时针旋转90度。位置:(- 1,1)方向:南。
            “G”:移动一步。位置:(- 1,0)方向:南。
            “L”:逆时针旋转90度。位置:(- 1,0)方向:东方。
            “G”:移动一步。位置:(0,0)方向:东方。
            “L”:逆时针旋转90度。位置:(0,0)方向:北。
            重复指令，机器人进入循环:(0,0)——>(0,1)——>(- 1,1)——>(- 1,0)——>(0,0)。
            在此基础上，我们返回true。

    提示：
        1 <= instructions.length <= 100
        instructions[i]仅包含'G', 'L', 'R'
    */

    public static void main(String[] args) {
        String instructions = "GGLLGG";
        String instructions2 = "GG";
        String instructions3 = "GL";

        System.out.println(isRobotBounded(instructions));
        System.out.println(isRobotBounded(instructions2));
        System.out.println(isRobotBounded(instructions3));
    }

    private static boolean isRobotBounded(String instructions) {
        // 记录东、南、西、北的操作
        // 左转90度就是idx--，右转90度就是idx++
        int[][] ops = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

        int idx = 3, x = 0, y = 0;
        for (int i = 0; i < instructions.length(); i++) {
            char c = instructions.charAt(i);
            if (c == 'G') {
                x += ops[idx][0];
                y += ops[idx][1];
            } else if (c == 'L') {
                idx = (idx - 1 + 4) % 4;
            } else if (c == 'R') {
                idx = (idx + 1) % 4;
            }
        }
        // 执行完一次后，
        // 如果仍在原点，则不论方向朝哪都会进入循环，只不过最后的朝向会变
        // 如果不在原点
        // 朝北，则永远不会回到原点
        // 朝南，则下一次执行完后，会与这一次的操作完全反向，回到原点，且朝北
        // 朝东，则下一次执行完后，会与这一次的操作完全反向，回到原点，且朝西
        // 朝西，与朝东类似
        return (x == 0 && y == 0) || idx != 3;
    }
}
