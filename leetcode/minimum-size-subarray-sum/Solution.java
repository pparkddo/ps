class Solution {

    public int minSubArrayLen(int target, int[] nums) {
        if (nums.length == 1) {
            return nums[0] >= target ? 1 : 0;
        }

        int left = 0;
        int right = 0;
        int sum = nums[left];

        int answer = Integer.MAX_VALUE;

        while (left <= right) {
            if (sum >= target) {
                answer = Math.min(answer, right - left + 1);
                sum -= nums[left];
                left++;
                continue;
            }
            right++;
            if (right == nums.length) {
                break;
            }
            sum += nums[right];
        }

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
}