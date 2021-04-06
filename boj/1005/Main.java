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

    private static int[] topologicalSort(Map<Integer, List<Integer>> graph, int[] inDegreeCounts, int[] times) {
        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[inDegreeCounts.length];

        for (int index = 1; index < inDegreeCounts.length; index++) {
            if (inDegreeCounts[index] == 0) {
                queue.add(index);
                result[index] = times[index];
            }
        }

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int each : graph.getOrDefault(vertex, new ArrayList<>())) {
                result[each] = Math.max(result[each], result[vertex]+times[each]);
                if (--inDegreeCounts[each] == 0) {
                    queue.add(each);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int testCase = 0; testCase < t; testCase++) {
            String[] nk = in.readLine().split(" ");
            int n = Integer.parseInt(nk[0]);
            int k = Integer.parseInt(nk[1]);
            String[] timesString = in.readLine().split(" ");
            int[] times = new int[n+1];
            for (int i = 1; i < times.length; i++) {
                times[i] = Integer.parseInt(timesString[i-1]);
            }
            int[] inDegreeCounts = new int[n+1];

            Map<Integer, List<Integer>> graph = new HashMap<>();
            for (int i = 0; i < k; i++) {
                String[] each = in.readLine().split(" ");
                int a = Integer.parseInt(each[0]);
                int b = Integer.parseInt(each[1]);
                List<Integer> edges = graph.getOrDefault(a, new ArrayList<>());
                edges.add(b);
                graph.put(a, edges);
                inDegreeCounts[b]++;
            }

            int w = Integer.parseInt(in.readLine());
            int[] result = topologicalSort(graph, inDegreeCounts, times);

            answers.append(result[w]).append("\n");
        }
        in.close();

        System.out.println(answers.toString().trim());
    }
}
