package com.wa.model;

/**
 * ListNode
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-02-04 19:41
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
