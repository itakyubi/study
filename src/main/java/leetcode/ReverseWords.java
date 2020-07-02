package leetcode;

/**
 * leetcode.ReverseWords
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-02-06 17:32
 */
public class ReverseWords {

    public String reverseWords(String s) {
        s = s.trim();
        if (s == null || s.length() == 0)
            return s;
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            while (s.charAt(i) == ' ')
                i--;
            int j = i;
            while (i > 0 && s.charAt(i) != ' ')
                i--;
            sb.append(s, i + 1, j + 1);
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
