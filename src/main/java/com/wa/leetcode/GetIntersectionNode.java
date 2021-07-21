package com.wa.leetcode;

import com.wa.model.ListNode;

/**
 * GetIntersectionNode
 * 2021-07-21 10:55
 *
 * @author wuao
 */
public class GetIntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        if (pA == null || pB == null)
            return null;

        while (pA != pB) {
            if (pA == null) {
                pA = headB;
            } else {
                pA = pA.next;
            }
            if (pB == null) {
                pB = headA;
            } else {
                pB = pB.next;
            }
        }
        return pA;
    }
}
