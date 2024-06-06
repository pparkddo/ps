class Solution {

    private int n = 0;
    private int k;
    private int answer;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        traverse(root);
        return answer;
    }

    private void traverse(TreeNode node) {
        if (node == null) {
            return;
        }
        traverse(node.left);
        if (++n == k) {
            answer = node.val;
            return;
        }
        traverse(node.right);
    }
}