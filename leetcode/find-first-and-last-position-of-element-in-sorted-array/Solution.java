class Solution {

    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        int leftIndex = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
                continue;
            }
            if (nums[mid] == target) {
                leftIndex = mid;
            }
            right = mid - 1;
        }

        left = 0;
        right = nums.length - 1;
        int rightIndex = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
                continue;
            }
            if (nums[mid] == target) {
                rightIndex = mid;
            }
            left = mid + 1;
        }

        return new int[] {leftIndex, rightIndex};
    }
}
