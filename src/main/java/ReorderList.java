/**
 * ReorderList
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-01-17 15:31
 */
public class ReorderList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void reorderList(ListNode head) {
        if (head == null)
            return;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode left = head;
        ListNode right = slow.next;
        slow.next = null;

        if (right != null) {
            ListNode h = new ListNode(0);
            h.next = right;
            ListNode p = right;
            ListNode tmp;
            while (p.next != null) {
                tmp = p.next;
                p.next = tmp.next;
                tmp.next = h.next;
                h.next = tmp;
            }
            right = h.next;
        }

        ListNode t1, t2;
        while (left.next != null && right != null) {
            t1 = left.next;
            t2 = right.next;
            left.next = right;
            right.next = t1;
            left = t1;
            right = t2;
        }
    }

}
