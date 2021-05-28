import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    private static final int UNREACHABLE = 1_000 * 100 + 1;

    private static String getAnswer(int[][] distances, int destination) {
        int answer = UNREACHABLE;
        for (int cost = 0; cost < distances[0].length; cost++) {
            answer = Math.min(answer, distances[destination][cost]);
        }
        return answer == UNREACHABLE ? "Poor KCM" : String.valueOf(answer);
    }

    private static String solution(int n, int m, int k, Map<Integer, List<Edge>> graph) {
        int[][] distances = new int[n+1][m+1];
        for (int[] each : distances) {
            Arrays.fill(each, UNREACHABLE);
        }

        int source = 1;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(source, 0, 0));
        distances[source][0] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (edge.to == n) {
                break;
            }
            for (Edge next : graph.get(edge.to)) {
                int c = edge.cost + next.cost;
                int d = edge.distance + next.distance;
                if (c > m) {
                    continue;
                }
                if (distances[next.to][c] > d) {
                    distances[next.to][c] = d;
                    pq.add(new Edge(next.to, c, d));
                }
            }
        }

        return getAnswer(distances, n);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answers = new StringBuilder();
        int t = Integer.parseInt(in.readLine());
        for (int testCase = 0; testCase < t; testCase++) {
            String[] nmk = in.readLine().split(" ");
            int n = Integer.parseInt(nmk[0]);
            int m = Integer.parseInt(nmk[1]);
            int k = Integer.parseInt(nmk[2]);
            Map<Integer, List<Edge>> graph = new HashMap<>();
            for (int v = 1; v <= n; v++) {
                graph.put(v, new ArrayList<>());
            }
            for (int i = 0; i < k; i++) {
                String[] uvcd = in.readLine().split(" ");
                int u = Integer.parseInt(uvcd[0]);
                int v = Integer.parseInt(uvcd[1]);
                int c = Integer.parseInt(uvcd[2]);
                int d = Integer.parseInt(uvcd[3]);
                graph.get(u).add(new Edge(v, c, d));
            }
            answers.append(solution(n, m, k, graph)).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}

class Edge implements Comparable<Edge> {

    int to;
    int cost;
    int distance;

    Edge(int to, int cost, int distance) {
        this.to = to;
        this.cost = cost;
        this.distance = distance;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(distance, o.distance);
    }

    @Override
    public String toString() {
        return to + " " + cost + " " + distance;
    }
}
