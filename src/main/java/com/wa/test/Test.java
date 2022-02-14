package com.wa.test;

import com.wa.model.ListNode;

/**
 * Test
 * 2021-10-18 19:23
 *
 * @author wuao
 */
public class Test {

    private static ListNode newHead = null;

    public static void main(String[] args) throws Exception {
        // 头插法
        for (int i = 0; i < 10; i++) {
            ListNode head = genLinkedList();
            Thread t1 = new Thread(() -> {
                newHead = insertAhead(head);
                printList(1, newHead);
            });
            Thread t2 = new Thread(() -> {
                newHead = insertAhead(head);
                //printList(2, newHead);
            });

            t1.start();
            t2.start();

            Thread.sleep(50);
            newHead = null;
            System.out.println();
        }

        System.out.println("---------------------");

        // 尾插法

        for (int i = 0; i < 10; i++) {
            ListNode head2 = genLinkedList();
            newHead = null;
            Thread t1 = new Thread(() -> newHead = insertTail(head2));
            Thread t2 = new Thread(() -> newHead = insertTail(head2));

            t1.start();
            t2.start();

            Thread.sleep(50);
            printList(newHead);
        }
    }

    private static ListNode insertAhead(ListNode head) {
        ListNode newHead = null;
        ListNode e = head;
        ListNode next;
        while (e != null) {
            next = e.next;
            e.next = newHead;
            newHead = e;
            e = next;
        }
        return newHead;
    }

    private static ListNode insertTail(ListNode head) {
        ListNode headNode = null, tailNode = null;
        while (head != null) {
            if (tailNode == null) {
                tailNode = head;
                headNode = head;
            } else {
                tailNode.next = head;
                tailNode = head;
            }
            head = head.next;
        }
        return headNode;
    }

    private static ListNode genLinkedList() {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 1; i <= 20; i++) {
            ListNode node = new ListNode(i);
            cur.next = node;
            cur = node;
        }
        return head;
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + ",");
            head = head.next;
        }
        System.out.println();
    }

    private static void printList(int id, ListNode head) {
        System.out.print(id + ":");
        while (head != null) {
            System.out.print(head.val + ",");
            head = head.next;
        }
        System.out.println();
    }

}
