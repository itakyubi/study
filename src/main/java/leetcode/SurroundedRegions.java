package leetcode;

/**
 * leetcode.SurroundedRegions
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-01-14 15:07
 */
public class SurroundedRegions {

    private char[][] board;
    private int n;
    private int m;

    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0)
            return;
        this.board = board;
        this.n = board.length;
        this.m = board[0].length;
        for (int i = 0; i < m; i++) {
            if (board[0][i] == 'O') {
                solve(0, i);
            }
            if (board[n - 1][i] == 'O') {
                solve(n - 1, i);
            }
        }
        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O') {
                solve(i, 0);
            }
            if (board[i][m - 1] == 'O') {
                solve(i, m - 1);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '*') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void solve(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] == 'X' || board[x][y] == '*')
            return;
        board[x][y] = '*';
        solve(x + 1, y);
        solve(x, y + 1);
        solve(x - 1, y);
        solve(x, y - 1);
    }
}
