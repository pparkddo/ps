class Solution {
    
    private int getRight(int[] nums) {
        int right = nums.length - 1;
        for (int i = nums.length-1; i >= 0; i--) {
            if (nums[i] != 0) {
                right = i;
                break;
            }
        }
        return right;
    }
    
    private int getLeft(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                return i;
            }
        }
        return nums.length - 1;
    }
    
    private void moveZero(int[] nums, int left, int right) {
        for (int i = left; i < right; i++) {
            int temp = nums[i+1];
            nums[i+1] = nums[i];
            nums[i] = temp;
        }
    }
    
    private boolean isEnd(int[] nums, int left, int right) {
        for (int i = left; i <= right; i++) {
            if (nums[i] != 0) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isZeroExists(int[] nums) {
        for (int num : nums) {
            if (num == 0) {
                return true;
            }
        }
        return false;
    }
    
    public void moveZeroes(int[] nums) {
        if (!isZeroExists(nums)) {
            return;
        }
        
        while (true) {
            int left = getLeft(nums);
            int right = getRight(nums);
            if (isEnd(nums, left, right)) {
                return;
            }
            moveZero(nums, left, right);
        }
    }
}
