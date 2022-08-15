package com.wa.leetcode;

/**
 * MyCircularDeque
 * https://leetcode.cn/problems/design-circular-deque/
 * 641. 设计循环双端队列
 * 2022/8/15 4:51 下午
 *
 * @author wuao
 */
public class MyCircularDeque {

    /*
    设计实现双端队列。
    实现 MyCircularDeque 类:
    MyCircularDeque(int k)：构造函数,双端队列最大为 k 。
    boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true，否则返回 false 。
    boolean insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true，否则返回 false 。
    boolean deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true，否则返回 false 。
    boolean deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true，否则返回 false 。
    int getFront())：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
    int getRear()：获得双端队列的最后一个元素。如果双端队列为空，返回 -1 。
    boolean isEmpty()：若双端队列为空，则返回true，否则返回 false 。
    boolean isFull()：若双端队列满了，则返回true，否则返回 false 。
            
    示例 1：
        输入 ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
            [[3], [1], [2], [3], [4], [], [], [], [4], []]
        输出 [null, true, true, true, false, 2, true, true, true, 4]
        解释
        MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
        circularDeque.insertLast(1);			        // 返回 true
        circularDeque.insertLast(2);			        // 返回 true
        circularDeque.insertFront(3);			        // 返回 true
        circularDeque.insertFront(4);			        // 已经满了，返回 false
        circularDeque.getRear();  				// 返回 2
        circularDeque.isFull();				        // 返回 true
        circularDeque.deleteLast();			        // 返回 true
        circularDeque.insertFront(4);			        // 返回 true
        circularDeque.getFront();				// 返回 4

    提示：
        1 <= k <= 1000
        0 <= value <= 1000
        insertFront,insertLast,deleteFront,deleteLast,getFront,getRear,isEmpty,isFull 调用次数不大于2000次
    */

    static class Node {
        Node prev;
        Node next;
        int val;

        Node() {
        }

        Node(int val) {
            this.val = val;
        }
    }

    private final Node head;
    private final Node tail;
    private final int capacity;
    private int size;

    public MyCircularDeque(int k) {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        capacity = k;
    }

    public boolean insertFront(int value) {
        if (isFull())
            return false;
        Node node = new Node(value);
        node.prev = head;
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull())
            return false;
        Node node = new Node(value);
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty())
            return false;
        head.next = head.next.next;
        head.next.prev = head;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty())
            return false;
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        size--;
        return true;
    }

    public int getFront() {
        return isEmpty() ? -1 : head.next.val;
    }

    public int getRear() {
        return isEmpty() ? -1 : tail.prev.val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
