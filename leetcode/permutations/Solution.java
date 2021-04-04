import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    
    int[] nums;
    Integer[] numbers;
    Set<Integer> visited = new HashSet<>();
    List<List<Integer>> answer = new ArrayList<>();
    
    private void dfs(int numberIndex, int depth) {
        if (depth == nums.length) {
            List<Integer> permutation = new ArrayList<>(Arrays.asList(numbers));
            answer.add(permutation);
            return;
        }
        for (int index = 0; index < nums.length; index++) {
            int number = nums[index];
            if (visited.contains(number)) {
                continue;
            }
            visited.add(number);
            numbers[depth] = number;
            dfs(index, depth+1);
            visited.remove(number);
        }
    }
    
    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        this.numbers = new Integer[nums.length];
        dfs(0, 0);
        return answer;
    }
}
