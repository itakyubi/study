package com.wa.leetcode;

import com.wa.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * PrintTree
 * https://leetcode.cn/problems/print-binary-tree/
 * 655. 输出二叉树
 * 2022/8/22 5:04 下午
 *
 * @author wuao
 */
public class PrintTree {

    /*
    给你一棵二叉树的根节点 root ，请你构造一个下标从 0 开始、大小为 m x n 的字符串矩阵 res ，用以表示树的 格式化布局 。构造此格式化布局矩阵需要遵循以下规则：
    树的 高度 为 height ，矩阵的行数 m 应该等于 height + 1 。
    矩阵的列数 n 应该等于 2^(height+1) - 1 。
    根节点 需要放置在 顶行 的 正中间 ，对应位置为 res[0][(n-1)/2] 。
    对于放置在矩阵中的每个节点，设对应位置为 res[r][c] ，将其左子节点放置在 res[r+1][c-2^(height-r-1)] ，右子节点放置在 res[r+1][c+2^(height-r-1)] 。
    继续这一过程，直到树中的所有节点都妥善放置。
    任意空单元格都应该包含空字符串 "" 。
    返回构造得到的矩阵 res 。

    示例 1：
        输入：root = [1,2]
        输出：[["","1",""],
              ["2","",""]]
    示例 2：
        输入：root = [1,2,3,null,4]
        输出：[["","","","1","","",""],
              ["","2","","","","3",""],
              ["","","4","","","",""]]

    提示：
        树中节点数在范围 [1, 210] 内
        -99 <= Node.val <= 99
        树的深度在范围 [1, 10] 内
    */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(printTree(root));
    }

    public static List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root) - 1;
        int m = height + 1;
        int n = (1 << height + 1) - 1;
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                list.add("");
            }
            res.add(list);
        }
        helper(res, 0, (n - 1) / 2, root, height);
        return res;
    }

    private static int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    private static void helper(List<List<String>> list, int row, int col, TreeNode node, int height) {
        list.get(row).set(col, String.valueOf(node.val));
        if (node.left != null) {
            helper(list, row + 1, col - (1 << height - row - 1), node.left, height);
        }
        if (node.right != null) {
            helper(list, row + 1, col + (1 << height - row - 1), node.right, height);
        }
    }
}
