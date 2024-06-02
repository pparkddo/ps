class Solution {

    int[] preorder;
    int[] inorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        return build(0, this.inorder.length - 1, 0);
    }

    private TreeNode build(int inorderStartIndex, int inorderEndIndex, int preorderIndex) {
        if (inorderStartIndex > inorderEndIndex) {
            return null;
        }
        TreeNode mid = new TreeNode(this.preorder[preorderIndex]);
        int inorderIndex = getInorderIndex(mid.val);
        int leftTreeLength = inorderIndex - inorderStartIndex + 1;
        mid.left = build(inorderStartIndex, inorderIndex - 1, preorderIndex + 1);
        mid.right = build(inorderIndex + 1, inorderEndIndex, leftTreeLength + preorderIndex);
        return mid;
    }

    private int getInorderIndex(int value) {
        for (int index = 0; index < this.inorder.length; index++) {
            if (this.inorder[index] == value) {
                return index;
            }
        }
        return -1;
    }
}