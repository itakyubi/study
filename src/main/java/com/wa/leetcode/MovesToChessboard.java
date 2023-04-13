package com.wa.leetcode;

/**
 * MovesToChessboard
 * https://leetcode.cn/problems/transform-to-chessboard/
 * 782. 变为棋盘
 * 2022/8/23 3:31 下午
 *
 * @author wuao
 */
public class MovesToChessboard {

    /*
    一个n x n的二维网络board仅由0和1组成。每次移动，你能任意交换两列或是两行的位置。
    返回 将这个矩阵变为 “棋盘”所需的最小移动次数。如果不存在可行的变换，输出 -1。
    “棋盘” 是指任意一格的上下左右四个方向的值均与本身不同的矩阵。

    示例 1:
        输入: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
        输出: 2
        解释:一种可行的变换方式如下，从左到右：
            第一次移动交换了第一列和第二列。
            第二次移动交换了第二行和第三行。
    示例 2:
        输入: board = [[0, 1], [1, 0]]
        输出: 0
        解释: 注意左上角的格值为0时也是合法的棋盘，也是合法的棋盘.
    示例 3:
        输入: board = [[1, 0], [1, 0]]
        输出: -1
        解释: 任意的变换都不能使这个输入变为合法的棋盘。

    提示：
        n == board.length
        n == board[i].length
        2 <= n <= 30
        board[i][j]将只包含0或1
    */

    public static void main(String[] args) {
        int[][] board = new int[][]{{0, 1, 1, 0}, {0, 1, 1, 0}, {1, 0, 0, 1}, {1, 0, 0, 1}};
        int[][] board2 = new int[][]{{0, 1}, {1, 0}};
        int[][] board3 = new int[][]{{1, 0}, {1, 0}};

        System.out.println(movesToChessboard(board));
        System.out.println(movesToChessboard(board2));
        System.out.println(movesToChessboard(board3));
    }

    private static int movesToChessboard(int[][] board) {
        return 0;
    }
}
