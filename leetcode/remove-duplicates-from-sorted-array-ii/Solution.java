class Solution {

    public int removeDuplicates(int[] nums) {
        int currentIndex = 1;
        int lastNumber = nums[0];
        int count = 1;
        for (int index = 1; index < nums.length; index++) {
            if (lastNumber < nums[index]) {
                nums[currentIndex++] = nums[index];
                lastNumber = nums[index];
                count = 1;
            }
            else if (lastNumber == nums[index] && count < 2) {
                nums[currentIndex++] = nums[index];
                count++;
            }
        }
        return currentIndex;
    }
}
