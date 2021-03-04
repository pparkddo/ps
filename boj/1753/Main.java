import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

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
    public int compareTo(Edge edge) {
        return Integer.compare(this.weight, edge.weight);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] ve = in.readLine().split(" ");
        int v = Integer.parseInt(ve[0]);
        int e = Integer.parseInt(ve[1]);
        int source = Integer.parseInt(in.readLine()) - 1;
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < v; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            String[] edgeInformation = in.readLine().split(" ");
            int from = Integer.parseInt(edgeInformation[0]) - 1;
            int to = Integer.parseInt(edgeInformation[1]) - 1;
            int weight = Integer.parseInt(edgeInformation[2]);
            graph.get(from).add(new Edge(to, weight));
        }
        in.close();

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        int[] distances = new int[v];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        boolean[] visited = new boolean[v];

        priorityQueue.add(new Edge(source, 0));
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            int destination = edge.to;
            if (visited[destination]) {
                continue;
            }
            visited[destination] = true;
            for (Edge each : graph.get(destination)) {
                if (distances[each.to] >= distances[destination] + each.weight) {
                    distances[each.to] = distances[destination] + each.weight;
                    priorityQueue.add(new Edge(each.to, distances[each.to]));
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int distance : distances) {
            answer.append(distance == Integer.MAX_VALUE ? "INF" : distance).append("\n");
        }
        System.out.println(answer.toString().trim());
    }
}
