package com.wa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LongestDecomposition
 * https://leetcode.cn/problems/longest-chunked-palindrome-decomposition/
 * 1147. 段式回文
 * 2023/4/12 8:56 上午
 *
 * @author wuao
 */
public class LongestDecomposition {

    /*
    你会得到一个字符串text。你应该把它分成 k个子字符串(subtext1, subtext2，…， subtextk)，要求满足:
    subtexti是 非空字符串
    所有子字符串的连接等于 text ( 即subtext1+ subtext2+ ... + subtextk== text)
    对于所有 i的有效值( 即1 <= i<= k ) ，subtexti== subtextk - i + 1 均成立
    返回k可能最大值。

    示例 1：
        输入：text = "ghiabcdefhelloadamhelloabcdefghi"
        输出：7
        解释：我们可以把字符串拆分成 "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)"。
    示例 2：
        输入：text = "merchant"
        输出：1
        解释：我们可以把字符串拆分成 "(merchant)"。
    示例 3：
        输入：text = "antaprezatepzapreanta"
        输出：11
        解释：我们可以把字符串拆分成 "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)"。

    提示：
        1 <= text.length <= 1000
        text仅由小写英文字符组成
    */

    public static void main(String[] args) {
        String text = "ghiabcdefhelloadamhelloabcdefghi";
        String text2 = "merchant";
        String text3 = "antaprezatepzapreanta";
        String text4 = "aaa";
        String text5 = "abcxabxc";

        System.out.println(longestDecomposition(text));
        System.out.println(longestDecomposition(text2));
        System.out.println(longestDecomposition(text3));
        System.out.println(longestDecomposition(text4));
        System.out.println(longestDecomposition(text5));
    }

    private static int longestDecomposition(String text) {
        // 分别从前往后和从后往前遍历
        // 如果两边的字母集合相同，则遍历是否顺序也相同
        Map<Character, Integer> map = new HashMap<>();
        int n = text.length();
        int res = 0;
        int l = 0, r = n - 1;
        int lastLeft = -1, lastRight = n;
        while (l < r) {
            char c1 = text.charAt(l);
            char c2 = text.charAt(r);

            map.put(c1, map.getOrDefault(c1, 0) + 1);
            if (map.get(c1) == 0) {
                map.remove(c1);
            }
            map.put(c2, map.getOrDefault(c2, 0) - 1);
            if (map.get(c2) == 0) {
                map.remove(c2);
            }

            if (map.size() == 0) {
                boolean flag = true;
                for (int i = lastLeft + 1; i <= l; i++) {
                    if (text.charAt(i) != text.charAt(lastRight - (l - i) - 1)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    res++;
                    lastLeft = l;
                    lastRight = r;
                }
            }
            l++;
            r--;
        }
        if (map.size() != 0 || lastRight - lastLeft > 1) {
            return res * 2 + 1;
        }
        return res * 2;
    }
}
