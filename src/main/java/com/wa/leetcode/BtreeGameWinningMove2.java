package com.wa.leetcode;

import com.wa.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BtreeGameWinningMove2
 * https://leetcode.cn/problems/binary-tree-coloring-game/
 * 1145. 二叉树着色游戏
 * 2023/2/3 3:58 下午
 *
 * @author wuao
 */
public class BtreeGameWinningMove2 {

    /*
    有两位极客玩家参与了一场「二叉树着色」的游戏。游戏中，给出二叉树的根节点root，树上总共有 n 个节点，且 n 为奇数，其中每个节点上的值从1 到n各不相同。
    最开始时：
            「一号」玩家从 [1, n]中取一个值x（1 <= x <= n）；
            「二号」玩家也从[1, n]中取一个值y（1 <= y <= n）且y != x。
            「一号」玩家给值为x的节点染上红色，而「二号」玩家给值为y的节点染上蓝色。
    之后两位玩家轮流进行操作，「一号」玩家先手。每一回合，玩家选择一个被他染过色的节点，将所选节点一个 未着色 的邻节点（即左右子节点、或父节点）进行染色（「一号」玩家染红色，「二号」玩家染蓝色）。
    如果（且仅在此种情况下）当前玩家无法找到这样的节点来染色时，其回合就会被跳过。
    若两个玩家都没有可以染色的节点时，游戏结束。着色节点最多的那位玩家获得胜利 ✌️。
    现在，假设你是「二号」玩家，根据所给出的输入，假如存在一个y值可以确保你赢得这场游戏，则返回true ；若无法获胜，就请返回 false 。

    示例 1 ：
        输入：root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
        输出：true
        解释：第二个玩家可以选择值为 2 的节点。
    示例 2 ：
        输入：root = [1,2,3], n = 3, x = 1
        输出：false

    提示：
        树中节点数目为 n
        1 <= x <= n <= 100
        n 是奇数
        1 <= Node.val <= n
        树中所有值 互不相同
    */

    /*public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }*/

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        // 遍历树找到节点x
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode xNode = null;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val == x) {
                xNode = node;
                break;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        // 找到x左子树的节点个数、右子树的节点个数、剩下的节点个数
        int leftCnt = count(xNode.left);
        int rightCnt = count(xNode.right);
        int parentCnt = n - leftCnt - rightCnt - 1;

        int max = Math.max(leftCnt, Math.max(rightCnt, parentCnt));
        return max > n - max;
    }

    private int count(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftCnt = count(node.left);
        int rightCnt = count(node.right);
        return leftCnt + rightCnt + 1;
    }
}
