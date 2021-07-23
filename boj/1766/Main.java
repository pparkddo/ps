import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    private static String solution(int n, int m, int[] inDegreeCounts, Map<Integer, List<Integer>> graph) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int problem = 1; problem < inDegreeCounts.length; problem++) {
            int inDegreeCount = inDegreeCounts[problem];
            if (inDegreeCount == 0) {
                pq.add(problem);
            }
        }

        StringBuilder answer = new StringBuilder();
        while (!pq.isEmpty()) {
            Integer problem = pq.poll();
            answer.append(problem).append(" ");
            for (Integer each : graph.get(problem)) {
                inDegreeCounts[each]--;
                if (inDegreeCounts[each] == 0) {
                    pq.add(each);
                }
            }
        }
        return answer.toString().trim();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[] inDegreeCounts = new int[n+1];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int v = 1; v <= n; v++) {
            graph.put(v, new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            String[] each = in.readLine().split(" ");
            int prev = Integer.parseInt(each[0]);
            int next = Integer.parseInt(each[1]);
            inDegreeCounts[next]++;
            graph.get(prev).add(next);
        }
        in.close();
        System.out.println(solution(n, m, inDegreeCounts, graph));
    }
}
