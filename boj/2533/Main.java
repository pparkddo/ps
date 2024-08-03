import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final Map<Integer, List<Integer>> GRAPH = new HashMap<>();
    private static int[][] DP;
    private static boolean[] VISITED;

    public static void main(String[] args) throws IOException {
        var in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());

        for (int i = 0; i < n - 1; i++) {
            String[] ab = in.readLine().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);

            List<Integer> aEdge = GRAPH.getOrDefault(a, new ArrayList<>());
            aEdge.add(b);
            GRAPH.put(a, aEdge);

            List<Integer> bEdge = GRAPH.getOrDefault(b, new ArrayList<>());
            bEdge.add(a);
            GRAPH.put(b, bEdge);
        }

        DP = new int[n+1][2];
        VISITED = new boolean[n+1];

        dfs(1);

        System.out.println(Math.min(DP[1][0], DP[1][1]));
    }

    private static void dfs(int v) {
        VISITED[v] = true;

        DP[v][0] = 0;
        DP[v][1] = 1;

        for (var next : GRAPH.getOrDefault(v, Collections.emptyList())) {
            if (VISITED[next]) {
                continue;
            }
            dfs(next);
            DP[v][0] += DP[next][1];
            DP[v][1] += Math.min(DP[next][0], DP[next][1]);
        }
    }
}
