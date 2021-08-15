class Solution {

    private int count = 0;
    private int answer = -1;

    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return answer;
    }

    public void traverse(TreeNode node, int k) {
        if (node.left != null) {
            traverse(node.left, k);
        }
        count++;
        if (count == k) {
            answer = node.val;
            return;
        }
        if (node.right != null) {
            traverse(node.right, k);
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