class Solution {

    TreeNode root = new TreeNode();

    public TreeNode sortedArrayToBST(int[] nums) {
        return transform(0, nums.length-1, nums);
    }

    private TreeNode transform(int startIndex, int endIndex, int[] nums) {
        if (startIndex > endIndex) {
            return null;
        }
        int mid = (startIndex + endIndex) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = transform(startIndex, mid-1, nums);
        node.right = transform(mid+1, endIndex, nums);
        return node;
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