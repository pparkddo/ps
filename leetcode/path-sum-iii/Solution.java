import java.util.HashMap;
import java.util.Map;

class Solution {

    public int pathSum(TreeNode root, int targetSum) {
        Map<Integer, Integer> counts = new HashMap<>();
        counts.put(0, 1);
        return getMatchCount(root, 0, targetSum, counts);
    }

    private int getMatchCount(TreeNode node, int sum, int targetSum, Map<Integer, Integer> counts) {
        if (node == null) {
            return 0;
        }

        sum += node.val;

        int result = counts.getOrDefault(sum-targetSum, 0);
        counts.put(sum, counts.getOrDefault(sum, 0)+1);
        result += getMatchCount(node.left, sum, targetSum, counts);
        result += getMatchCount(node.right, sum, targetSum, counts);
        counts.put(sum, counts.get(sum)-1);
        return result;
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
 