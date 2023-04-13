package com.wa.leetcode;

import java.util.Arrays;

/**
 * ExamRoom
 * https://leetcode.cn/problems/exam-room/
 * 855. 考场就座
 * 2022/12/30 4:17 下午
 *
 * @author wuao
 */
public class ExamRoom {

    /*
    在考场里，一排有N个座位，分别编号为0, 1, 2, ..., N-1。
    当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
    返回ExamRoom(int N)类，它有两个公开的函数：其中，函数ExamRoom.seat()会返回一个int（整型数据），代表学生坐的位置；函数ExamRoom.leave(int p)代表坐在座位 p 上的学生现在离开了考场。每次调用ExamRoom.leave(p)时都保证有学生坐在座位p上。

    示例：
        输入：["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
        输出：[null,0,9,4,2,null,5]
        解释：
        ExamRoom(10) -> null
        seat() -> 0，没有人在考场里，那么学生坐在 0 号座位上。
        seat() -> 9，学生最后坐在 9 号座位上。
        seat() -> 4，学生最后坐在 4 号座位上。
        seat() -> 2，学生最后坐在 2 号座位上。
        leave(4) -> null
        seat() -> 5，学生最后坐在 5 号座位上。

    提示：
        1 <= N <= 10^9
        在所有的测试样例中ExamRoom.seat()和ExamRoom.leave()最多被调用10^4次。
        保证在调用ExamRoom.leave(p)时有学生正坐在座位 p 上。
    */

    private int[] left;
    private int[] right;
    private int[] seat;

    public ExamRoom(int n) {
        this.left = new int[n];
        this.right = new int[n];
        this.seat = new int[n];

        Arrays.fill(this.right, n - 1);
    }

    public int seat() {
        int max = 0, maxIndex = 0;
        for (int i = 0; i < seat.length; i++) {
            int distance = Math.min(i - left[i], right[i] - i);
            if (distance > max) {
                max = distance;
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public void leave(int p) {

    }


    public static void main(String[] args) {
        ExamRoom obj = new ExamRoom(10);
        System.out.println(obj.seat());
        System.out.println(obj.seat());
        System.out.println(obj.seat());
        System.out.println(obj.seat());
        obj.leave(4);
        System.out.println(obj.seat());
    }
}
