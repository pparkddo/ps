import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<List<Integer>> graph = new ArrayList<>();
    private static int[] parents;
    private static boolean[] visited;

    private static void dfs(int n) {
        visited[n] = true;
        for (Integer each : graph.get(n)) {
            if (visited[each]) {
                continue;
            }
            parents[each] = n;
            dfs(each);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        parents = new int[n];
        visited = new boolean[n];
        for (int i = 1; i < n; i++) {
            String[] each = in.readLine().split(" ");
            int a = Integer.parseInt(each[0]) - 1;
            int b = Integer.parseInt(each[1]) - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        in.close();

        dfs(0);

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i < n; i++) {
            answer.append(parents[i]+1).append("\n");
        }
        System.out.println(answer.toString().trim());
    }
}
