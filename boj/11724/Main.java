import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    
    static Map<Integer, List<Integer>> graph;
    static boolean[] visited;
    static int[] components;

    private static void dfs(int node, int id) {
        if (visited[node]) {
            return;
        }
        visited[node] = true;
        components[node] = id;
        for (Integer each : graph.get(node)) {
            dfs(each, id);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        graph = new HashMap<>();
        for (int node = 1; node <= n; node++) {
            graph.put(node, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] each = in.readLine().split(" ");
            int a = Integer.parseInt(each[0]);
            int b = Integer.parseInt(each[1]);
            List<Integer> aNodes = graph.get(a);
            List<Integer> bNodes = graph.get(b);
            aNodes.add(b);
            bNodes.add(a);
        }
        in.close();

        visited = new boolean[n+1];
        components = new int[n+1];

        int id = 0;
        for (int node = 1; node <= n; node++) {
            dfs(node, id++);
        }

        System.out.println(Arrays.stream(components).distinct().count());
    }
}
