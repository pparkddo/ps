import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class Main {

    private static final int UNREACHABLE = Integer.MAX_VALUE;

    private static String solution(int n, int m, Map<Integer, List<Edge>> graph, int source, int destination) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(source, 0));

        int[] distances = new int[n+1];
        Arrays.fill(distances, UNREACHABLE);
        distances[source] = 0;

        boolean[] visited = new boolean[n+1];

        int[] parents = new int[n+1];

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int to = edge.to;
            if (visited[to]) {
                continue;
            }
            visited[to] = true;
            for (Edge each : graph.getOrDefault(to, new ArrayList<>())) {
                if (distances[each.to] >= distances[to] + each.weight) {
                    distances[each.to] = distances[to] + each.weight;
                    parents[each.to] = to;
                    pq.add(new Edge(each.to, distances[each.to]));
                }
            }
        }

        StringBuilder answer = new StringBuilder();

        Stack<Integer> routes = getRoutes(parents, source, destination);
        answer.append(distances[destination]).append("\n").append(routes.size()).append("\n");
        while (!routes.isEmpty()) {
            answer.append(routes.pop()).append(" ");
        }

        return answer.toString();
    }

    private static Stack<Integer> getRoutes(int[] parents, int source, int destination) {
        Stack<Integer> routes = new Stack<>();

        int current = destination;
        while (current != source) {
            routes.push(current);
            current = parents[current];
        }
        routes.push(current);

        return routes;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int m = Integer.parseInt(in.readLine());
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String[] each = in.readLine().split(" ");
            int a = Integer.parseInt(each[0]);
            int b = Integer.parseInt(each[1]);
            int w = Integer.parseInt(each[2]);
            List<Edge> edges = graph.getOrDefault(a, new ArrayList<>());
            edges.add(new Edge(b, w));
            graph.put(a, edges);
        }
        String[] sd = in.readLine().split(" ");
        int source = Integer.parseInt(sd[0]);
        int destination = Integer.parseInt(sd[1]);
        in.close();
        System.out.println(solution(n, m, graph, source, destination));
    }
}

class Edge implements Comparable<Edge> {

    int to;
    int weight;

    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }
}
