package com.wa.leetcode;

import com.wa.model.ListNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * NextLargerNodes
 * https://leetcode.cn/problems/next-greater-node-in-linked-list/
 * 1019. 链表中的下一个更大节点
 * 2023/4/11 9:11 上午
 *
 * @author wuao
 */
public class NextLargerNodes {

    /*
    给定一个长度为n的链表head
    对于列表中的每个节点，查找下一个 更大节点 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。
    返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。如果第 i 个节点没有下一个更大的节点，设置answer[i] = 0。

    示例 1：
        输入：head = [2,1,5]
        输出：[5,5,0]
    示例 2：
        输入：head = [2,7,4,3,5]
        输出：[7,0,5,5,0]

    提示：
        链表中节点数为n
        1 <= n <= 10^4
        1 <= Node.val <= 10^9
    */

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(5);
        head.next = node1;
        node1.next = node2;

        ListNode head2 = new ListNode(2);
        ListNode node21 = new ListNode(7);
        ListNode node22 = new ListNode(4);
        ListNode node23 = new ListNode(3);
        ListNode node24 = new ListNode(5);
        head2.next = node21;
        node21.next = node22;
        node22.next = node23;
        node23.next = node24;

        System.out.println(Arrays.toString(nextLargerNodes(head)));
        System.out.println(Arrays.toString(nextLargerNodes(head2)));

    }


    public static int[] nextLargerNodes(ListNode head) {
        Stack<int[]> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        ListNode cur = head;
        int index = -1;
        while (cur != null) {
            index++;
            while (!stack.isEmpty() && stack.peek()[1] < cur.val) {
                int[] tmp = stack.pop();
                map.put(tmp[0], cur.val);
            }
            stack.push(new int[]{index, cur.val});
            cur = cur.next;
        }

        int[] res = new int[index + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = map.getOrDefault(i, 0);
        }
        return res;
    }
}
