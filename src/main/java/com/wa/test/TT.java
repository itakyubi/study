package com.wa.test;

import com.wa.model.ListNode;

/**
 * TT
 *
 * @author: wuao
 * @time: 2021/5/24 20:55
 **/
public class TT {

    private static ListNode tailNode = null;
    private static ListNode headNode = null;

    public static void main(String[] args) {
        ListNode head = genLinkedList();
        //printList(head);

        Thread t1 = new Thread(() -> insertTail(head));
        Thread t2 = new Thread(() -> insertTail(head));

        t1.start();
        t2.start();

        printList(headNode);

    }

    private static void insertTail(ListNode head) {
        while (head != null) {
            if (tailNode == null) {
                tailNode = head;
                headNode = head;
            } else {
                tailNode.next = head;
                tailNode = head;
            }
            head = head.next;
            //printList2(headNode);
        }
    }

    private static ListNode genLinkedList() {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 1; i <= 10; i++) {
            ListNode node = new ListNode(i);
            cur.next = node;
            cur = node;
        }
        return head;
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    private static void printList2(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            System.out.print(",");
            head = head.next;
        }
        System.out.println();
    }
}
