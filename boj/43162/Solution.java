import java.util.Map;
import java.util.HashMap;

class Solution {
    
    int[][] computers;
    Map<Integer, Integer> networks = new HashMap<>();
    
    private void dfs(int vertex, int id) {
        networks.put(vertex, id);
        for (int i = 0; i < computers[0].length; i++) {
            if (vertex == i) {
                continue;
            }
            if (computers[vertex][i] == 0) {
                continue;
            }
            if (networks.containsKey(i)) {
                continue;
            }
            dfs(i, id);
        }
    }
    
    public int solution(int n, int[][] computers) {
        this.computers = computers;
        int id = 0;
        for (int i = 0; i < computers.length; i++) {
            for (int j = 0; j < computers[0].length; j++) {
                if (networks.containsKey(j)) {
                    continue;
                }
                if (computers[i][j] == 0) {
                    continue;
                }
                dfs(j, id);
            }
            id++;
        }
        return (int) networks.values().stream().distinct().count();
    }
}
