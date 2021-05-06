import java.util.ArrayList;
import java.util.List;

class Solution {
    
    private void dfs(int start, int depth, int[] numbers, int[] nums, List<List<Integer>> answer) {
        List<Integer> subset = new ArrayList<>();
        for (int i = 0; i < depth; i++) {
            subset.add(nums[numbers[i]]);
        }
        answer.add(subset);
        for (int index = start; index < nums.length; index++) {
            numbers[depth] = index;
            dfs(index+1, depth+1, numbers, nums, answer);
        }
    }
    
    public List<List<Integer>> subsets(int[] nums) {
        int[] numbers = new int[nums.length];
        List<List<Integer>> answer = new ArrayList<>();
        dfs(0, 0, numbers, nums, answer);
        return answer;
    }
}