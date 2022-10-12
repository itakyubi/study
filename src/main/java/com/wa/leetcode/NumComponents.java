package com.wa.leetcode;

import com.wa.model.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * NumComponents
 * https://leetcode.cn/problems/linked-list-components/
 * 817. 链表组件
 * 2022/10/12 5:16 下午
 *
 * @author wuao
 */
public class NumComponents {

    /*
    给定链表头结点head，该链表上的每个结点都有一个 唯一的整型值 。同时给定列表nums，该列表是上述链表中整型值的一个子集。
    返回列表nums中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表nums中）构成的集合。

    示例1：
        输入: head = [0,1,2,3], nums = [0,1,3]
        输出: 2
        解释: 链表中,0 和 1 是相连接的，且 nums 中不包含 2，所以 [0, 1] 是 nums 的一个组件，同理 [3] 也是一个组件，故返回 2。
    示例 2：
        输入: head = [0,1,2,3,4], nums = [0,3,1,4]
        输出: 2
        解释: 链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。

    提示：
        链表中节点数为n
        1 <= n <= 104
        0 <= Node.val < n
        Node.val中所有值 不同
        1 <= nums.length <= n
        0 <= nums[i] < n
        nums 中所有值 不同
    */

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        System.out.println(numComponents(head, new int[]{0, 1, 3}));

        node3.next = node4;
        System.out.println(numComponents(head, new int[]{0, 3, 1, 4}));
    }

    private static int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int res = 0;
        ListNode cur = head;
        while (cur != null) {
            boolean flag = false;
            while (cur != null && set.contains(cur.val)) {
                flag = true;
                cur = cur.next;
            }
            while (cur != null && !set.contains(cur.val)) {
                cur = cur.next;
            }
            if (flag)
                res++;
        }
        return res;
    }
}
