package com.wa.leetcode;

import java.util.Stack;

/**
 * ValidateStackSequences
 * https://leetcode.cn/problems/validate-stack-sequences/
 * 946. 验证栈序列
 * 2022/8/31 9:18 上午
 *
 * @author wuao
 */
public class ValidateStackSequences {

    /*
    给定pushed和popped两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false。

    示例 1：
        输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
        输出：true
        解释：我们可以按以下顺序执行：
        push(1), push(2), push(3), push(4), pop() -> 4,
        push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
    示例 2：
        输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
        输出：false
        解释：1 不能在 2 之前弹出。
            
    提示：
        1 <= pushed.length <= 1000
        0 <= pushed[i] <= 1000
        pushed 的所有元素 互不相同
        popped.length == pushed.length
        popped 是 pushed 的一个排列
    */

    public static void main(String[] args) {
        int[] pushed = new int[]{1, 2, 3, 4, 5}, popped = new int[]{4, 5, 3, 2, 1};
        int[] pushed2 = new int[]{1, 2, 3, 4, 5}, popped2 = new int[]{4, 3, 5, 1, 2};

        System.out.println(validateStackSequences2(pushed, popped));
        System.out.println(validateStackSequences2(pushed2, popped2));
    }

    private static boolean validateStackSequences(int[] pushed, int[] popped) {
        int i = 0, j = 0;
        Stack<Integer> stack = new Stack<>();
        while (i < pushed.length && j < popped.length) {
            if (stack.isEmpty() || stack.peek() != popped[j]) {
                stack.push(pushed[i]);
                i++;
            } else {
                while (!stack.isEmpty() && j < popped.length && stack.peek() == popped[j]) {
                    stack.pop();
                    j++;
                }
            }
        }
        while (!stack.isEmpty() && j < popped.length && stack.peek() == popped[j]) {
            stack.pop();
            j++;
        }
        return stack.isEmpty();
    }

    private static boolean validateStackSequences2(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0, j = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
