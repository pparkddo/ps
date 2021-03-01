import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {

    private static final int ROOT = -1;

    private int find(int[] parents, int n) {
        if (parents[n] == ROOT) {
            return n;
        }
        return parents[n] = find(parents, parents[n]);
    }

    private boolean union(int[] parents, int a, int b) {
        a = find(parents, a);
        b = find(parents, b);
        if (a == b) {
            return false;
        }
        parents[b] = a;
        return true;
    }

    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));
        Set<Integer> vertices = new HashSet<>();
        for (int i = 0; i < costs.length; i++) {
            vertices.add(costs[i][0]);
            vertices.add(costs[i][1]);
        }
        int v = vertices.size();
        int[] parents = new int[v];
        Arrays.fill(parents, ROOT);
        int edgeCount = 0;
        int answer = 0;
        for (int i = 0; i < costs.length; i++) {
            if (edgeCount == v-1) {
                break;
            }
            if (!union(parents, costs[i][0], costs[i][1])) {
                continue;
            };
            answer += costs[i][2];
            edgeCount++;
        }
        return answer;
    }
}
