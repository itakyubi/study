package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * CamelMatch
 * https://leetcode.cn/problems/camelcase-matching/
 * 1023. 驼峰式匹配
 * 2023/4/14 8:48 上午
 *
 * @author wuao
 */
public class CamelMatch {

    /*
    如果我们可以将小写字母插入模式串pattern得到待查询项query，那么待查询项与给定模式串匹配。（我们可以在任何位置插入每个字符，也可以插入 0 个字符。）
    给定待查询列表queries，和模式串pattern，返回由布尔值组成的答案列表answer。只有在待查项queries[i] 与模式串pattern 匹配时，answer[i]才为 true，否则为 false。

    示例 1：
        输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
        输出：[true,false,true,true,false]
        示例：
            "FooBar" 可以这样生成："F" + "oo" + "B" + "ar"。
            "FootBall" 可以这样生成："F" + "oot" + "B" + "all".
            "FrameBuffer" 可以这样生成："F" + "rame" + "B" + "uffer".
    示例 2：
        输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
        输出：[true,false,true,false,false]
        解释：
            "FooBar" 可以这样生成："Fo" + "o" + "Ba" + "r".
            "FootBall" 可以这样生成："Fo" + "ot" + "Ba" + "ll".
    示例 3：
        输出：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
        输入：[false,true,false,false,false]
        解释：
            "FooBarTest" 可以这样生成："Fo" + "o" + "Ba" + "r" + "T" + "est".

    提示：
        1 <= queries.length <= 100
        1 <= queries[i].length <= 100
        1 <= pattern.length <= 100
        所有字符串都仅由大写和小写英文字母组成。
    */

    public static void main(String[] args) {
        String[] queries = {"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"};
        String pattern = "FB";

        String[] queries2 = {"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"};
        String pattern2 = "FoBa";

        String[] queries3 = {"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"};
        String pattern3 = "FoBaT";

        System.out.println(camelMatch(queries, pattern));
        System.out.println(camelMatch(queries2, pattern2));
        System.out.println(camelMatch(queries3, pattern3));
    }

    private static List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> res = new ArrayList<>();
        for (String query : queries) {
            int i = 0, j = 0;
            boolean flag = true;
            while (i < query.length() && j < pattern.length()) {
                if (query.charAt(i) == pattern.charAt(j)) {
                    i++;
                    j++;
                } else {
                    if (query.charAt(i) >= 'a' && query.charAt(i) <= 'z') {
                        i++;
                    } else {
                        flag = false;
                        break;
                    }
                }
            }
            if (!flag) {
                res.add(false);
            } else {
                if (j != pattern.length()) {
                    res.add(false);
                } else if (i == query.length()) {
                    res.add(true);
                } else {
                    boolean tmpRes = true;
                    for (int k = i; k < query.length(); k++) {
                        if (query.charAt(k) < 'a' || query.charAt(k) > 'z') {
                            tmpRes = false;
                            break;
                        }
                    }
                    res.add(tmpRes);
                }
            }


        }
        return res;
    }
}
