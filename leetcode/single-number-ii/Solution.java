class Solution {

    public int singleNumber(int[] nums) {
        int x1 = 0;
        int x2 = 0;
        int mask = 0;

        for (var num : nums) {
            x2 ^= num & x1;  // second seen
            x1 ^= num;  // first seen
            mask = ~(x1 & x2);  // third seen
            x1 &= mask;
            x2 &= mask;
        }

        return x1;
    }
}