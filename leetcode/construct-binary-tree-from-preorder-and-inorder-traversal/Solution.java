public class Solution {

    int[] preorder;
    int[] inorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        return construct(0, preorder.length-1, 0);
    }

    private TreeNode construct(int startIndex, int endIndex, int index) {
        if (startIndex > endIndex || index > preorder.length) {
            return null;
        }
        TreeNode mid = new TreeNode(preorder[index]);
        int inorderIndex = getInorderIndex(mid.val);
        int leftSubTreeLength = inorderIndex - startIndex + 1;
        mid.left = construct(startIndex, inorderIndex-1, index+1);
        mid.right = construct(inorderIndex+1, endIndex, leftSubTreeLength+index);
        return mid;
    }

    private int getInorderIndex(int value) {
        for (int index = 0; index < inorder.length; index++) {
            if (inorder[index] == value) {
                return index;
            }
        }
        throw new IllegalArgumentException("value does not exists: " + value);
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