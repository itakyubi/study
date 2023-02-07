package com.wa.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * AlertNames
 * https://leetcode.cn/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/
 * 1604. 警告一小时内使用相同员工卡大于等于三次的人
 * 2023/2/7 9:12 上午
 *
 * @author wuao
 */
public class AlertNames {

    /*
    力扣公司的员工都使用员工卡来开办公室的门。每当一个员工使用一次他的员工卡，安保系统会记录下员工的名字和使用时间。如果一个员工在一小时时间内使用员工卡的次数大于等于三次，这个系统会自动发布一个 警告。
    给你字符串数组keyName和keyTime ，其中[keyName[i], keyTime[i]]对应一个人的名字和他在某一天 内使用员工卡的时间。
    使用时间的格式是 24小时制，形如"HH:MM"，比方说"23:51" 和"09:49"。
    请你返回去重后的收到系统警告的员工名字，将它们按 字典序升序排序后返回。
    请注意"10:00" - "11:00"视为一个小时时间范围内，而"23:51" - "00:10"不被视为一小时内，因为系统记录的是某一天内的使用情况。

    示例 1：
        输入：keyName = ["daniel","daniel","daniel","luis","luis","luis","luis"], keyTime = ["10:00","10:40","11:00","09:00","11:00","13:00","15:00"]
        输出：["daniel"]
        解释："daniel" 在一小时内使用了 3 次员工卡（"10:00"，"10:40"，"11:00"）。
    示例 2：
        输入：keyName = ["alice","alice","alice","bob","bob","bob","bob"], keyTime = ["12:01","12:00","18:00","21:00","21:20","21:30","23:00"]
        输出：["bob"]
        解释："bob" 在一小时内使用了 3 次员工卡（"21:00"，"21:20"，"21:30"）。
    示例 3：
        输入：keyName = ["john","john","john"], keyTime = ["23:58","23:59","00:01"]
        输出：[]
    示例 4：
        输入：keyName = ["leslie","leslie","leslie","clare","clare","clare","clare"], keyTime = ["13:00","13:20","14:00","18:00","18:51","19:30","19:49"]
        输出：["clare","leslie"]

    提示：
        1 <= keyName.length, keyTime.length <= 105
        keyName.length == keyTime.length
        keyTime 格式为"HH:MM"。
        保证[keyName[i], keyTime[i]]形成的二元对互不相同。
        1 <= keyName[i].length <= 10
        keyName[i]只包含小写英文字母。
    */

    public static void main(String[] args) {
        String[] keyName = new String[]{"daniel", "daniel", "daniel", "luis", "luis", "luis", "luis"}, keyTime = new String[]{"10:00", "10:40", "11:00", "09:00", "11:00", "13:00", "15:00"};
        String[] keyName2 = new String[]{"alice", "alice", "alice", "bob", "bob", "bob", "bob"}, keyTime2 = new String[]{"12:01", "12:00", "18:00", "21:00", "21:20", "21:30", "23:00"};
        String[] keyName3 = new String[]{"john", "john", "john"}, keyTime3 = new String[]{"23:58", "23:59", "00:01"};
        String[] keyName4 = new String[]{"leslie", "leslie", "leslie", "clare", "clare", "clare", "clare"}, keyTime4 = new String[]{"13:00", "13:20", "14:00", "18:00", "18:51", "19:30", "19:49"};
        String[] keyName5 = new String[]{"a", "a", "a", "a", "a", "b", "b", "b", "b", "b", "b"}, keyTime5 = new String[]{"23:20", "11:09", "23:30", "23:02", "15:28", "22:57", "23:40", "03:43", "21:55", "20:38", "00:19"};

        System.out.println(alertNames(keyName, keyTime));
        System.out.println(alertNames(keyName2, keyTime2));
        System.out.println(alertNames(keyName3, keyTime3));
        System.out.println(alertNames(keyName4, keyTime4));
        System.out.println(alertNames(keyName5, keyTime5));
    }

    private static List<String> alertNames(String[] keyName, String[] keyTime) {
        // 根据keyName分割，记录每个人使用工卡的时间
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < keyName.length; i++) {
            List<String> list = map.getOrDefault(keyName[i], new ArrayList<>());
            list.add(keyTime[i]);
            map.put(keyName[i], list);
        }

        // 挨个判断每个人是否有告警
        Set<String> res = new HashSet<>();
        for (String name : map.keySet()) {
            List<String> times = map.get(name);
            Collections.sort(times);
            Deque<String> timeQueue = new LinkedList<>();
            for (String time : times) {
                if (timeQueue.size() >= 3) {
                    res.add(name);
                    break;
                }
                while (!timeQueue.isEmpty()) {
                    int diff = getTimeDiff(time, timeQueue.getFirst());
                    if (diff == -1) {
                        timeQueue.clear();
                        break;
                    } else if (diff > 60) {
                        timeQueue.removeFirst();
                    } else {
                        break;
                    }
                }
                timeQueue.offer(time);
            }
            if (timeQueue.size() >= 3) {
                res.add(name);
            }
        }
        return res.stream().sorted().collect(Collectors.toList());
    }

    // t1 - t2
    private static int getTimeDiff(String t1, String t2) {
        String[] timeStr1 = t1.split(":");
        String[] timeStr2 = t2.split(":");

        int[] time1 = new int[]{Integer.parseInt(timeStr1[0]), Integer.parseInt(timeStr1[1])};
        int[] time2 = new int[]{Integer.parseInt(timeStr2[0]), Integer.parseInt(timeStr2[1])};


        // 跨天
        if (time1[0] < time2[0]) {
            return -1;
        }

        return time1[1] - time2[1] + (time1[0] - time2[0]) * 60;
    }

    // 官方答案
    public List<String> alertNames2(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> timeMap = new HashMap<String, List<Integer>>();
        int n = keyName.length;
        for (int i = 0; i < n; i++) {
            String name = keyName[i];
            String time = keyTime[i];
            timeMap.putIfAbsent(name, new ArrayList<Integer>());
            int hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
            int minute = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
            timeMap.get(name).add(hour * 60 + minute);
        }
        List<String> res = new ArrayList<String>();
        Set<String> keySet = timeMap.keySet();
        for (String name : keySet) {
            List<Integer> list = timeMap.get(name);
            Collections.sort(list);
            int size = list.size();
            for (int i = 2; i < size; i++) {
                int time1 = list.get(i - 2), time2 = list.get(i);
                int difference = time2 - time1;
                if (difference <= 60) {
                    res.add(name);
                    break;
                }
            }
        }
        Collections.sort(res);
        return res;
    }
}
