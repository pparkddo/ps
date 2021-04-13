class Solution {

    public boolean canJump(int[] nums) {
        int max = nums[0];
        for (int index = 1; index < nums.length-1; index++) {
            if (index > max) {
                break;
            }
            int num = nums[index];
            max = Math.max(max, index+num);
        }
        return max >= nums.length-1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().canJump(new int[] {2,3,1,1,4}));
        System.out.println(new Solution().canJump(new int[] {3,2,1,0,4}));
        System.out.println(new Solution().canJump(new int[] {0,2,3}));
        System.out.println(new Solution().canJump(new int[] {0}));
    }
}