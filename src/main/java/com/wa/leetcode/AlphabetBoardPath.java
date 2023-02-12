package com.wa.leetcode;

/**
 * AlphabetBoardPath
 * https://leetcode.cn/problems/alphabet-board-path/
 * 1138. 字母板上的路径
 * 2023/2/12 10:35 上午
 *
 * @author wuao
 */
public class AlphabetBoardPath {

    /*
    我们从一块字母板上的位置(0, 0)出发，该坐标对应的字符为board[0][0]。
    在本题里，字母板为board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"]，如下所示。

    我们可以按下面的指令规则行动：
    如果方格存在，'U'意味着将我们的位置上移一行；
    如果方格存在，'D'意味着将我们的位置下移一行；
    如果方格存在，'L'意味着将我们的位置左移一列；
    如果方格存在，'R'意味着将我们的位置右移一列；
    '!'会把在我们当前位置 (r, c) 的字符board[r][c]添加到答案中。
    （注意，字母板上只存在有字母的位置。）

    返回指令序列，用最小的行动次数让答案和目标target相同。你可以返回任何达成目标的路径。

    示例 1：
        输入：target = "leet"
        输出："DDR!UURRR!!DDD!"
    示例 2：
        输入：target = "code"
        输出："RR!DDRR!UUL!R!"

    提示：
        1 <= target.length <= 100
        target仅含有小写英文字母。
    */

    public static void main(String[] args) {
        String target = "leet";
        String target2 = "code";
        String target3 = "zb";
        String target4 = "zdz";

        System.out.println(alphabetBoardPath(target));
        System.out.println(alphabetBoardPath(target2));
        System.out.println(alphabetBoardPath(target3));
        System.out.println(alphabetBoardPath(target4));
    }

    private static String alphabetBoardPath(String target) {
        int startX = 0, startY = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            // 计算字母的坐标
            int endX = (c - 'a') / 5;
            int endY = (c - 'a') % 5;

            // 计算当前字母与上个字母的坐标差
            if (c == 'z') {
                addAlphabetWithCount(sb, endY - startY > 0 ? 'R' : 'L', Math.abs(endY - startY));
                addAlphabetWithCount(sb, endX - startX > 0 ? 'D' : 'U', Math.abs(endX - startX));
            } else {
                addAlphabetWithCount(sb, endX - startX > 0 ? 'D' : 'U', Math.abs(endX - startX));
                addAlphabetWithCount(sb, endY - startY > 0 ? 'R' : 'L', Math.abs(endY - startY));
            }
            sb.append("!");

            // 更新起点
            startX = endX;
            startY = endY;
        }
        return sb.toString();
    }

    private static void addAlphabetWithCount(StringBuilder sb, char c, int cnt) {
        for (int i = 0; i < cnt; i++) {
            sb.append(c);
        }
    }
}
