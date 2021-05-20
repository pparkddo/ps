class Solution {

    public int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];

        int product = 1;
        for (int index = 0; index < nums.length; index++) {
            answer[index] = product;
            product *= nums[index];
        }

        product = 1;
        for (int index = nums.length-1; index >= 0; index--) {
            answer[index] *= product;
            product *= nums[index];
        }

        return answer;
    }
}
