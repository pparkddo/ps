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

    private static String solution(int n, int m, Map<Integer, List<Integer>> graph, int[] inDegreeCounts) {
        Queue<Integer> queue = new LinkedList<>();
        for (int index = 1; index <= n; index++) {
            if (inDegreeCounts[index] == 0) {
                queue.add(index);
            }
        }
        StringBuilder result = new StringBuilder();
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.append(vertex).append(" ");
            for (Integer each : graph.get(vertex)) {
                inDegreeCounts[each]--;
                if (inDegreeCounts[each] == 0) {
                    queue.add(each);
                }
            }
        }
        return result.toString();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int v = 1; v <= n; v++) {
            graph.put(v, new ArrayList<>());
        }
        int[] inDegreeCounts = new int[n+1];
        for (int i = 0; i < m; i++) {
            String[] ab = in.readLine().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);
            graph.get(a).add(b);
            inDegreeCounts[b]++;
        }
        in.close();
        System.out.println(solution(n, m, graph, inDegreeCounts));
    }
}
