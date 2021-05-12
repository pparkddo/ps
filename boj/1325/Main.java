import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Main {

    private static int getCount(Map<Integer, List<Integer>> graph, int start, int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        boolean[] visited = new boolean[n+1];
        visited[start] = true;

        int totalCount = 0;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            totalCount++;
            for (int each : graph.get(vertex)) {
                if (visited[each]) {
                    continue;
                }
                visited[each] = true;
                queue.add(each);
            }
        }

        return totalCount;
    }

    private static String getAnswer(int[] counts, int max) {
        StringBuilder answer = new StringBuilder();
        for (int vertex = 1; vertex < counts.length; vertex++) {
            int count = counts[vertex];
            if (count == max) {
                answer.append(vertex).append(" ");
            }
        }
        return answer.toString();
    }

    private static String solution(int n, int m, Map<Integer, List<Integer>> graph) {
        int[] counts = new int[n+1];
        int max = Integer.MIN_VALUE;
        for (int vertex = 1; vertex <= n; vertex++) {
            int count = getCount(graph, vertex, n);
            counts[vertex] = count;
            max = Math.max(max, count);
        }
        return getAnswer(counts, max);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            String[] each = in.readLine().split(" ");
            int a = Integer.parseInt(each[0]);
            int b = Integer.parseInt(each[1]);
            graph.get(b).add(a);
        }
        in.close();
        System.out.println(solution(n, m, graph));
    }
}
