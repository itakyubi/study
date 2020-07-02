/**
 * CopyRandomList
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-01-16 16:51
 */
public class CopyRandomList {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null)
            return head;
        Node p = head;
        while (p != null) {
            Node node = new Node(p.val);
            node.next = p.next;
            p.next = node;
            p = node.next;
        }

        p = head;
        while (p != null) {
            p.next.random = p.random == null ? null : p.random.next;
            p = p.next.next;
        }

        Node oldHead = head;
        Node newHead = head.next;
        Node pHead = head.next;
        while (oldHead != null) {
            oldHead.next = newHead.next;
            newHead.next = newHead.next == null ? null : newHead.next.next;
            oldHead = oldHead.next;
            newHead = newHead.next;
        }

        return pHead;
    }
}
