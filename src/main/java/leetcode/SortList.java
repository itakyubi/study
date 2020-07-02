package leetcode;

import model.ListNode;

/**
 * leetcode.SortList
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-02-05 19:17
 */
public class SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode mid = slow.next;
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        ListNode h = new ListNode(0);
        ListNode tmp = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                tmp.next = left;
                left = left.next;
            } else {
                tmp.next = right;
                right = right.next;
            }
            tmp = tmp.next;
        }
        tmp.next = left != null ? left : right;
        return h.next;
    }
}
