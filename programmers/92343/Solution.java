import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {

    private final Map<Integer, List<Integer>> graph = new HashMap<>();
    private int[] info;
    private int answer = 0;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        for (var edge : edges) {
            List<Integer> e = graph.getOrDefault(edge[0], new ArrayList<>());
            e.add(edge[1]);
            graph.put(edge[0], e);
        }
        dfs(0, new HashSet<>(List.of(0)), 0, 0);
        return answer;
    }

    private void dfs(int node, Set<Integer> next, int sheep, int wolf) {
        if (info[node] == 0) {
            sheep++;
        } else {
            wolf++;
        }

        if (sheep <= wolf) {
            return;
        }

        answer = Math.max(answer, sheep);

        Set<Integer> newNext = new HashSet<>(next);
        var after = graph.get(node);
        if (after != null && !after.isEmpty()) {
            newNext.addAll(after);
        }
        newNext.remove(node);

        for (var n : newNext) {
            dfs(n, newNext, sheep, wolf);
        }
    }
}
