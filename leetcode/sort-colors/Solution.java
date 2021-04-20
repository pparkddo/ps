class Solution {
    public void sortColors(int[] nums) {
        int[] counts = new int[3];
        for (int num : nums) {
            counts[num]++;
        }
        int index = 0;
        for (int number = 0; number < counts.length; number++) {
            for (int i = 0; i < counts[number]; i++) {
                nums[index++] = number;
            }
        }
    }
}
