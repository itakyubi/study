import model.ListNode;

/**
 * InsertionSortList
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-02-04 19:41
 */
public class InsertionSortList {

    public ListNode insertionSortList(ListNode head) {
        ListNode h = new ListNode(0);
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            ListNode p = h;
            while (p.next != null && p.next.val < cur.val)
                p = p.next;
            cur.next = p.next;
            p.next = cur;
            cur = next;
        }
        return h.next;
    }
}
