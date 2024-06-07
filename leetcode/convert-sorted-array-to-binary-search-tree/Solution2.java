class Solution {

    private int[] nums;

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return buildTree(0, nums.length - 1);
    }

    private TreeNode buildTree(int start, int end) {
        if (end < 0 || start >= nums.length || start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildTree(start, mid - 1);
        node.right = buildTree(mid + 1, end);
        return node;
    }
}
