import java.util.ArrayList;
import java.util.List;

class Solution {
    
    int[] candidates;
    int target;
    List<List<Integer>> answer = new ArrayList<>();
    
    private void dfs(int index, List<Integer> numbers, int sum) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            answer.add(new ArrayList<>(numbers));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            int candidate = candidates[i];
            numbers.add(candidate);
            dfs(i, numbers, sum+candidate);
            numbers.remove(numbers.size()-1);
        }
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.candidates = candidates;
        this.target = target;
        dfs(0, new ArrayList<>(), 0);
        return answer;
    }
}
