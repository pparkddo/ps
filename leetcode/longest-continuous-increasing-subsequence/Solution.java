class Solution {

    public int findLengthOfLCIS(int[] nums) {
        int index = 1;
        int answer = 1;
        int count = 1;
        while (index < nums.length) {
            int previous = nums[index-1];
            int number = nums[index++];
            if (previous < number) {
                count++;
                answer = Math.max(answer, count);
            }
            else {
                count = 1;
            }
        }
        return answer;
    }
}