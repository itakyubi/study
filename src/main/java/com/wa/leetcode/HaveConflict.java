package com.wa.leetcode;

/**
 * HaveConflict
 * https://leetcode.cn/problems/determine-if-two-events-have-conflict/
 * 2446. 判断两个事件是否存在冲突
 * 2023/5/17 2:18 PM
 *
 * @author wuao
 */
public class HaveConflict {

    /*
    给你两个字符串数组 event1 和event2，表示发生在同一天的两个闭区间时间段事件，其中：
    event1 = [startTime1, endTime1] 且
    event2 = [startTime2, endTime2]
    事件的时间为有效的 24 小时制且按HH:MM格式给出。
    当两个事件存在某个非空的交集时（即，某些时刻是两个事件都包含的），则认为出现 冲突。
    如果两个事件之间存在冲突，返回true；否则，返回false 。

    示例 1：
        输入：event1 = ["01:15","02:00"], event2 = ["02:00","03:00"]
        输出：true
        解释：两个事件在 2:00 出现交集。
    示例 2：
        输入：event1 = ["01:00","02:00"], event2 = ["01:20","03:00"]
        输出：true
        解释：两个事件的交集从 01:20 开始，到 02:00 结束。
    示例 3：
        输入：event1 = ["10:00","11:00"], event2 = ["14:00","15:00"]
        输出：false
        解释：两个事件不存在交集。

    提示：
        evnet1.length == event2.length == 2.
        event1[i].length == event2[i].length == 5
        startTime1 <= endTime1
        startTime2 <= endTime2
        所有事件的时间都按照HH:MM格式给出
    */

    public static void main(String[] args) {
        String[] event1 = {"01:15", "02:00"}, event2 = {"02:00", "03:00"};
        String[] event12 = {"01:00", "02:00"}, event22 = {"01:20", "03:00"};
        String[] event13 = {"10:00", "11:00"}, event23 = {"14:00", "15:00"};
        String[] event14 = {"14:13", "22:08"}, event24 = {"02:40", "08:08"};

        System.out.println(haveConflict(event1, event2));
        System.out.println(haveConflict(event12, event22));
        System.out.println(haveConflict(event13, event23));
        System.out.println(haveConflict(event14, event24));
    }

    private static boolean haveConflict(String[] event1, String[] event2) {
        int tsStart1 = getTs(event1[0]);
        int tsEnd1 = getTs(event1[1]);
        int tsStart2 = getTs(event2[0]);
        int tsEnd2 = getTs(event2[1]);
        if (tsStart1 <= tsStart2) {
            return tsEnd1 >= tsStart2;
        } else {
            return tsEnd2 >= tsStart1;
        }
    }

    private static int getTs(String s) {
        String[] str = s.split(":");
        return Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
    }

    // 官方题解
    public boolean haveConflict2(String[] event1, String[] event2) {
        return !(event2[0].compareTo(event1[1]) > 0 || event2[1].compareTo(event1[0]) <0);
    }
}
