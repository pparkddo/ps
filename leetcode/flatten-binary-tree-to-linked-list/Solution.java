class Solution {

    public void flatten(TreeNode root) {
        traverse(root, null);
    }

    private TreeNode traverse(TreeNode node, TreeNode previous) {
        if (node == null) {
            return previous;
        } 
        previous = traverse(node.right, previous);
        previous = traverse(node.left, previous);
        node.right = previous;
        node.left = null;
        previous = node;
        return previous;
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
