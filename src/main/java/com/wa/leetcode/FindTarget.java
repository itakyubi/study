package com.wa.leetcode;

import com.wa.model.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * FindTarget
 * https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/
 * 2022-03-21 09:08
 *
 * @author wuao
 */
public class FindTarget {

    private Set<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);

        return findTarget(root.left, k) || findTarget(root.right, k);
    }
}
