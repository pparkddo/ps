import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {

    private static final int UNREACHABLE = 50_000 * 1_000;

    private static int[] getDistances(int n, Map<Integer, List<Edge>> graph, int source) {
        int[] distances = new int[n+1];
        Arrays.fill(distances, UNREACHABLE);
        distances[source] = 0;

        boolean[] visited = new boolean[n+1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(source, 0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int to = edge.to;
            if (visited[to]) {
                continue;
            }
            visited[to] = true;
            for (Edge each : graph.get(to)) {
                if (distances[each.to] > distances[to] + each.cost) {
                    distances[each.to] = distances[to] + each.cost;
                    pq.add(new Edge(each.to, distances[each.to]));
                }
            }
        }
        
        return distances;
    }
    
    private static String solution(int n, int m, int t, int s, int g, int h, Map<Integer, List<Edge>> graph, int[] candidates) {
        int[] distancesFromS = getDistances(n, graph, s);
        int[] distancesFromG = getDistances(n, graph, g);
        int[] distancesFromH = getDistances(n, graph, h);

        List<Integer> answer = new ArrayList<>();
        for (int each : candidates) {
            int distance = distancesFromS[each];
            if (distance == distancesFromS[g]+distancesFromG[h]+distancesFromH[each]) {
                answer.add(each);
                continue;
            }
            if (distance == distancesFromS[h]+distancesFromH[g]+distancesFromG[each]) {
                answer.add(each);
            }
        }
        return answer.stream().sorted().map(String::valueOf).collect(Collectors.joining(" "));
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answers = new StringBuilder();
        int T = Integer.parseInt(in.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            String[] nmt = in.readLine().split(" ");
            int n = Integer.parseInt(nmt[0]);
            int m = Integer.parseInt(nmt[1]);
            int t = Integer.parseInt(nmt[2]);
            String[] sgh = in.readLine().split(" ");
            int s = Integer.parseInt(sgh[0]);
            int g = Integer.parseInt(sgh[1]);
            int h = Integer.parseInt(sgh[2]);
            Map<Integer, List<Edge>> graph = new HashMap<>();
            for (int v = 1; v <= n; v++) {
                graph.put(v, new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                String[] abd = in.readLine().split(" ");
                int a = Integer.parseInt(abd[0]);
                int b = Integer.parseInt(abd[1]);
                int d = Integer.parseInt(abd[2]);
                graph.get(a).add(new Edge(b, d));
                graph.get(b).add(new Edge(a, d));
            }
            int[] candidates = new int[t];
            for (int index = 0; index < t; index++) {
                candidates[index] = Integer.parseInt(in.readLine());
            }
            answers.append(solution(n, m, t, s, g, h, graph, candidates)).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}

class Edge implements Comparable<Edge> {

    int to;
    int cost;

    Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(cost, o.cost);
    }
    
    @Override
    public String toString() {
        return "Edge(" + to + ", " + cost + ")";
    }
}
