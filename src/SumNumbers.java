import model.TreeNode;

/**
 * SumNumbers
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-01-10 14:44
 */
public class SumNumbers {

    private int ans;
    private int tmp;

    public int sumNumbers(TreeNode root) {
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode node) {
        if (node == null)
            return;
        tmp = tmp * 10 + node.val;
        if (node.left == null && node.right == null)
            ans += tmp;
        if (node.left != null)
            dfs(node.left);
        if (node.right != null)
            dfs(node.right);
        tmp /= 10;
    }


}
