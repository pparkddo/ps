import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {

    private final Map<Integer, Set<Integer>> tree = new HashMap<>();

    public int solution(int n, int[][] wires) {
        for (int v = 1; v <= n; v++) {
            tree.put(v, new HashSet<>());
        }
        for (int[] wire : wires) {
            tree.get(wire[0]).add(wire[1]);
            tree.get(wire[1]).add(wire[0]);
        }
        int answer = Integer.MAX_VALUE;
        for (int[] wire : wires) {
            removeEdge(wire);
            int count = new DfsExecutor(n, tree).run(1);
            int diff = Math.abs((n-count)-count);
            restoreEdge(wire);
            answer = Math.min(answer, diff);
        }
        return answer;
    }

    private void removeEdge(int[] wire) {
        tree.get(wire[0]).remove(wire[1]);
        tree.get(wire[1]).remove(wire[0]);
    }

    private void restoreEdge(int[] wire) {
        tree.get(wire[0]).add(wire[1]);
        tree.get(wire[1]).add(wire[0]);
    }
}

class DfsExecutor {

    private boolean[] visited;
    private Map<Integer, Set<Integer>> tree;

    DfsExecutor(int n, Map<Integer, Set<Integer>> tree) {
        this.visited = new boolean[n+1];
        this.tree = tree;
    }

    public int run(int vertex) {
        if (visited[vertex]) {
            return 0;
        }
        visited[vertex] = true;
        int count = 1;
        for (Integer each : tree.get(vertex)) {
            count += run(each);
        }
        return count;
    }
}