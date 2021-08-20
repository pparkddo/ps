import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        traverse(root, values);
        return values;
    }

    private void traverse(TreeNode node, List<Integer> stack) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            traverse(node.left, stack);
        }
        stack.add(node.val);
        if (node.right != null) {
            traverse(node.right, stack);
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
 