package com.wa.leetcode;

/**
 * ContainVirus
 * https://leetcode.cn/problems/contain-virus/
 * 749. 隔离病毒
 * 2022-07-18 18:31
 *
 * @author wuao
 */
public class ContainVirus {

    /*
    病毒扩散得很快，现在你的任务是尽可能地通过安装防火墙来隔离病毒。
    假设世界由 m x n 的二维矩阵 isInfected 组成， isInfected[i][j] == 0 表示该区域未感染病毒，而  isInfected[i][j] == 1 表示该区域已感染病毒。可以在任意 2 个相邻单元之间的共享边界上安装一个防火墙（并且只有一个防火墙）。
    每天晚上，病毒会从被感染区域向相邻未感染区域扩散，除非被防火墙隔离。现由于资源有限，每天你只能安装一系列防火墙来隔离其中一个被病毒感染的区域（一个区域或连续的一片区域），且该感染区域对未感染区域的威胁最大且 保证唯一 。
    你需要努力使得最后有部分区域不被病毒感染，如果可以成功，那么返回需要使用的防火墙个数; 如果无法实现，则返回在世界被病毒全部感染时已安装的防火墙个数。

    示例 1：
        输入: isInfected = [[0,1,0,0,0,0,0,1],[0,1,0,0,0,0,0,1],[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0]]
        输出: 10
        解释:一共有两块被病毒感染的区域。
        在第一天，添加 5 墙隔离病毒区域的左侧。病毒传播后的状态是:
        第二天，在右侧添加 5 个墙来隔离病毒区域。此时病毒已经被完全控制住了。
    示例 2：
        输入: isInfected = [[1,1,1],[1,0,1],[1,1,1]]
        输出: 4
        解释: 虽然只保存了一个小区域，但却有四面墙。
        注意，防火墙只建立在两个不同区域的共享边界上。
    示例 3:
        输入: isInfected = [[1,1,1,0,0,0,0,0,0],[1,0,1,0,1,1,1,1,1],[1,1,1,0,0,0,0,0,0]]
        输出: 13
        解释: 在隔离右边感染区域后，隔离左边病毒区域只需要 2 个防火墙。

    提示:
        m == isInfected.length
        n == isInfected[i].length
        1 <= m, n <= 50
        isInfected[i][j] is either 0 or 1
        在整个描述的过程中，总有一个相邻的病毒区域，它将在下一轮 严格地感染更多未受污染的方块
    */

    public static void main(String[] args) {
        int[][] isInfected = new int[][]{{0, 1, 0, 0, 0, 0, 0, 1}, {0, 1, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0}};
        int[][] isInfected2 = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] isInfected3 = new int[][]{{1, 1, 1, 0, 0, 0, 0, 0, 0}, {1, 0, 1, 0, 1, 1, 1, 1, 1}, {1, 1, 1, 0, 0, 0, 0, 0, 0}};
        int[][] isInfected4 = new int[][]{{0, 1, 0, 1, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 1, 1, 0, 0, 0, 1, 0}, {0, 0, 0, 1, 1, 0, 0, 1, 1, 0}, {0, 1, 0, 0, 1, 0, 1, 1, 0, 1}, {0, 0, 0, 1, 0, 1, 0, 1, 1, 1}, {0, 1, 0, 0, 1, 0, 0, 1, 1, 0}, {0, 1, 0, 1, 0, 0, 0, 1, 1, 0}, {0, 1, 1, 0, 0, 1, 1, 0, 0, 1}, {1, 0, 1, 1, 0, 1, 0, 1, 0, 1}};

        System.out.println(containVirus(isInfected));
        System.out.println(containVirus(isInfected2));
        System.out.println(containVirus(isInfected3));
        System.out.println(containVirus(isInfected4));
    }

    private static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int rows;
    private static int cols;
    private static int[][] matrix;

    private static int containVirus(int[][] isInfected) {
        int res = 0;
        rows = isInfected.length;
        cols = isInfected[0].length;
        matrix = isInfected;
        while (true) {
            int count = getMaxAreaAndFireWallAndInactive();
            if (count == 0)
                break;
            res += count;
            diffusion();
        }
        return res;
    }

    private static int getMaxAreaAndFireWallAndInactive() {
        // 找到下一轮影响最大的病毒区域
        int maxArea = 0, maxFireWall = 0, targetX = -1, targetY = -1;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (visited[i][j] || matrix[i][j] != 1) {
                    continue;
                }
                boolean[][] visited2 = new boolean[rows][cols];
                int[] tmp = dfs(i, j, visited, visited2);
                if (tmp[1] > maxArea) {
                    maxFireWall = tmp[0];
                    maxArea = tmp[1];
                    targetX = i;
                    targetY = j;
                }
            }
        }

        if (targetX == -1)
            return 0;

        // 该区域的病毒灭活
        inactive(targetX, targetY);

        return maxFireWall;
    }

    private static int[] dfs(int i, int j, boolean[][] visited, boolean[][] visited2) {
        int fireWall = 0, area = 0;
        visited[i][j] = true;
        visited2[i][j] = true;
        for (int[] offset : offsets) {
            int x = i + offset[0], y = j + offset[1];
            if (x >= 0 && x < rows && y >= 0 && y < cols && !visited[x][y]) {
                if (matrix[x][y] == 0) {
                    if (!visited2[x][y]) {
                        area++;
                        visited2[x][y] = true;
                    }
                    fireWall++;
                } else if (matrix[x][y] == 1) {
                    int[] tmp = dfs(x, y, visited, visited2);
                    fireWall += tmp[0];
                    area += tmp[1];
                }
            }
        }
        return new int[]{fireWall, area};
    }

    private static void inactive(int i, int j) {
        matrix[i][j] = -1;
        for (int[] offset : offsets) {
            int x = i + offset[0], y = j + offset[1];
            if (x >= 0 && x < rows && y >= 0 && y < cols && matrix[x][y] == 1) {
                matrix[i][j] = -1;
                inactive(x, y);
            }
        }
    }

    private static void diffusion() {
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (visited[i][j] || matrix[i][j] != 1) {
                    continue;
                }
                for (int[] offset : offsets) {
                    int x = i + offset[0], y = j + offset[1];
                    if (x >= 0 && x < rows && y >= 0 && y < cols && !visited[x][y] && matrix[x][y] == 0) {
                        matrix[x][y] = 1;
                        visited[x][y] = true;
                    }
                }
            }
        }
    }
}
