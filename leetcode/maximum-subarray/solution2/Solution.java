class Solution {
    public int maxSubArray(int[] nums) {
        int answer = nums[0];

        int acc = nums[0];

        for (int index = 1; index < nums.length; index++) {
            if (acc + nums[index] > 0) {
                acc = Math.max(nums[index], acc + nums[index]);
            } else if (acc + nums[index] <= 0) {
                acc = nums[index];
            } else if (acc < nums[index]) {
                acc = nums[index];
            }
            // this code below can replace the above code
            // acc = Math.max(nums[index], acc + nums[index]);

            answer = Math.max(answer, acc);
        }

        return answer;
    }
}
