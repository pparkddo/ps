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

    private static int solution(int n, int m, int source, int destination, Map<Integer, List<Edge>> graph) {
        int[] distances = new int[n+1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        boolean[] visited = new boolean[n+1];
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();

        distances[source] = 0;
        priorityQueue.add(new Edge(source, 0));

        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            int dest = edge.to;
            if (visited[dest]) {
                continue;
            }
            visited[dest] = true;
            for (Edge each : graph.getOrDefault(dest, new ArrayList<>())) {
                if (distances[each.to] < distances[dest] + each.weight) {
                    continue;
                }
                distances[each.to] = distances[dest] + each.weight;
                priorityQueue.add(new Edge(each.to, distances[each.to]));
            }
        }

        return distances[destination];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int m = Integer.parseInt(in.readLine());
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String[] each = in.readLine().split(" ");
            int from = Integer.parseInt(each[0]);
            int to = Integer.parseInt(each[1]);
            int weight = Integer.parseInt(each[2]);
            List<Edge> destinations = graph.getOrDefault(from, new ArrayList<>());
            destinations.add(new Edge(to, weight));
            graph.put(from, destinations);
        }
        String[] se = in.readLine().split(" ");
        int source = Integer.parseInt(se[0]);
        int destination = Integer.parseInt(se[1]);
        in.close();
        System.out.println(solution(n, m, source, destination, graph));
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
    public String toString() {
        return this.to + " " + this.weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }
}
