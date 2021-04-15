class Solution {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // find lowest number
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
                continue;
            }
            right = mid;
        }

        int pivot = left;

        left = 0;
        right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int realMid = (mid + pivot) % nums.length;
            int number = nums[realMid];
            if (number == target) {
                return realMid;
            }
            if (number > target) {
                right = mid - 1;
                continue;
            }
            left = mid + 1;
        }

        return -1;
    }
}
