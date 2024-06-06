import java.util.ArrayList;
import java.util.List;

class Solution {

    private int n;
    private int k;
    private final List<List<Integer>> answer = new ArrayList<>();
    private final boolean[] visited = new boolean[21];

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        dfs(0, 0);
        return answer;
    }

    private void dfs(int value, int depth) {
        if (depth == k) {
            var a = new ArrayList<Integer>();
            for (int i = 1; i < visited.length; i++) {
                if (visited[i]) {
                    a.add(i);
                }
            }
            answer.add(a);
            return;
        }
        for (int i = value + 1; i <= n; i++) {
            visited[i] = true;
            dfs(i, depth+1);
            visited[i] = false;
        }
    }
}