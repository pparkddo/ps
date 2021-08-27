import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        traverse(root, 0, answer);
        return answer;
    }

    private void traverse(TreeNode node, int level, List<List<Integer>> values) {
        if (node == null) {
            return;
        }
        if (values.size() == level) {
            values.add(new ArrayList<>());
        }
        values.get(level).add(node.val);
        if (node.left != null) {
            traverse(node.left, level+1, values);
        }
        if (node.right != null) {
            traverse(node.right, level+1, values);
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
