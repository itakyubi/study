package com.wa.leetcode;

import com.wa.model.ListNode;

import java.util.Stack;

/**
 * AddTwoNumbersII
 * https://leetcode.cn/problems/add-two-numbers-ii/
 * 445. 两数相加 II
 * 2023/7/3 8:55 AM
 *
 * @author wuao
 */
public class AddTwoNumbersII {

    /*
    给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
    你可以假设除了数字 0 之外，这两个数字都不会以零开头。

    示例1：
        输入：l1 = [7,2,4,3], l2 = [5,6,4]
        输出：[7,8,0,7]
    示例2：
        输入：l1 = [2,4,3], l2 = [5,6,4]
        输出：[8,0,7]
    示例3：
        输入：l1 = [0], l2 = [0]
        输出：[0]

    提示：
        链表的长度范围为 [1, 100]
        0 <= node.val <= 9
        输入数据保证链表代表的数字无前导 0

    进阶：如果输入链表不能翻转该如何解决？
    */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.add(l2.val);
            l2 = l2.next;
        }

        ListNode head = new ListNode();
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            int sum = carry;
            if (!stack1.isEmpty()) {
                sum += stack1.pop();
            }
            if (!stack2.isEmpty()) {
                sum += stack2.pop();
            }

            if (sum < 10) {
                carry = 0;
            } else {
                carry = 1;
                sum -= 10;
            }
            ListNode next = new ListNode(sum);
            next.next = head.next;
            head.next = next;
        }
        return head.next;
    }
}
