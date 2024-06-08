class Solution {

    int[] nums;

    public int findMin(int[] nums) {
        this.nums = nums;
        return search(0, nums.length - 1);
    }

    private int search(int left, int right) {
        if (left == right) {
            return nums[left];
        }

        if (right - left == 1) {
            return Math.min(nums[left], nums[right]);
        }

        int mid = (left + right) / 2;

        boolean leftSideRotated = nums[left] > nums[mid];
        boolean rightSideRotated = nums[mid] > nums[right];

        if (leftSideRotated) {
            return search(left, mid);
        } else if (rightSideRotated) {
            return search(mid, right);
        } else {
            return search(left, mid);
        }
    }
}