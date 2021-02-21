import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    private Map<Integer, List<Integer>> graph;
    private Map<Integer, Boolean> colors = new HashMap<>();
    private boolean isBipartite = true;

    Solution(Map<Integer, List<Integer>> graph) {
        this.graph = graph;
    }

    public boolean getAnswer() {
        return this.isBipartite;
    }

    public void run() {
        for (Integer key: graph.keySet()) {
            if (colors.containsKey(key)) {
                continue;
            }
            dfs(key, true);
        }
    }

    private void dfs(int vertex, boolean flag) {
        if (!this.isBipartite) {
            return;
        }
        colors.put(vertex, flag);
        for (Integer each : graph.get(vertex)) {
            if (colors.containsKey(each)){
                if (colors.get(each) == flag) {
                    this.isBipartite = false;
                    return;
                }
                continue;
            }
            dfs(each, !flag);
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int i = 0; i < k; i++) {
            String[] ve = in.readLine().split(" ");
            int v = Integer.parseInt(ve[0]);
            int e = Integer.parseInt(ve[1]);
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for (int j = 1; j <= v; j++) {
                graph.put(j, new ArrayList<>());
            }
            for (int j = 0; j < e; j++) { 
                String[] each = in.readLine().split(" ");
                int a = Integer.parseInt(each[0]);
                int b = Integer.parseInt(each[1]);
                graph.get(a).add(b);
                graph.get(b).add(a);
            }
            Solution solution = new Solution(graph);
            solution.run();
            answers.append(solution.getAnswer() ? "YES" : "NO").append("\n");
        }
        System.out.println(answers.toString().trim());
        in.close();
    }
}
