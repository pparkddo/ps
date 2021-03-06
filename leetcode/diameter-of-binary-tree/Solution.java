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
 
class Solution {
    
    int answer;
    
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        answer = Math.max(answer, left+right+1);
        return Math.max(left, right) + 1;
    }
 
    public int diameterOfBinaryTree(TreeNode root) {
        answer = 1;
        dfs(root);
        return answer - 1;
    }
}
