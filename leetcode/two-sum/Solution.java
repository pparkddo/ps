import java.util.Arrays;

public class Solution {

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    return new int[] {i, j};
                }
            }
        }
        throw new IllegalArgumentException();
    }
}

class Main {
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().twoSum(new int[] {2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(new Solution().twoSum(new int[] {3, 2, 4}, 6)));
        System.out.println(Arrays.toString(new Solution().twoSum(new int[] {3, 3}, 6)));
        System.out.println(Arrays.toString(new Solution().twoSum(new int[] {0, 4, 3, 0}, 0)));
        System.out.println(Arrays.toString(new Solution().twoSum(new int[] {-1, -2, -3, -4, -5}, -8)));
    }

}
