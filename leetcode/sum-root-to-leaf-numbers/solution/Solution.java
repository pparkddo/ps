class Solution {

    int answer = 0;

    public int sumNumbers(TreeNode root) {
        dfs(0, root);
        return answer;
    }

    private void dfs(int value, TreeNode node) {
        int newValue = value * 10 + node.val;

        if (node.left == null && node.right == null) {
            answer += newValue;
            return;
        }

        if (node.left != null) {
            dfs(newValue, node.left);
        }

        if (node.right != null) {
            dfs(newValue, node.right);
        }
    }
}
