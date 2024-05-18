class Solution {

    public int removeDuplicates(int[] nums) {
        int currentIndex = 1;
        int lastNumber = nums[0];
        for (int index = 0; index < nums.length; index++) {
            if (lastNumber < nums[index]) {
                 nums[currentIndex++] = nums[index];
                 lastNumber = nums[index];
            }
        }
        return currentIndex;
    }
}
