import model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * PreorderTraversal
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-02-03 17:06
 */
public class PreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
            res.add(node.val);
        }
        return res;
    }
}
