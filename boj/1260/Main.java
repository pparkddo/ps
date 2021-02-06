import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class DFS {

    private Map<Integer, List<Integer>> graph;
    private Set<Integer> visit;
    private StringBuilder answer;

    DFS(Map<Integer, List<Integer>> graph) {
        this.graph = graph;
        this.visit = new HashSet<>();
        this.answer = new StringBuilder();
    }

    public void run(int vertex) {
        if (visit.contains(vertex)) {
            return;
        }
        this.answer.append(vertex).append(" ");
        for (int edge : graph.get(vertex)) {
            visit.add(vertex);
            run(edge);
        }
    }

    public String getAnswer() {
        return answer.toString().stripTrailing();
    }
}

class BFS {

    private Map<Integer, List<Integer>> graph;
    private Set<Integer> visit;
    private StringBuilder answer;

    BFS(Map<Integer, List<Integer>> graph) {
        this.graph = graph;
        this.visit = new HashSet<>();
        this.answer = new StringBuilder();
    }

    public void run(int initialVertex) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(initialVertex);
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            if (visit.contains(vertex)) {
                continue;
            }
            this.answer.append(vertex).append(" ");
            for (int edge : graph.get(vertex)) {
                visit.add(vertex);
                queue.add(edge);
            }
        }
    }

    public String getAnswer() {
        return answer.toString().stripTrailing();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int m = input[1];
        int v = input[2];

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<Integer>());
        }

        for (int i = 0; i < m; i++) {
            int[] edges = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph.get(edges[0]).add(edges[1]);
            graph.get(edges[1]).add(edges[0]);
        }
        in.close();

        for (Map.Entry<Integer, List<Integer>> element : graph.entrySet()) {
            element.getValue().sort(Comparator.naturalOrder());
        }

        DFS dfs = new DFS(graph);
        dfs.run(v);
        System.out.println(dfs.getAnswer());

        BFS bfs = new BFS(graph);
        bfs.run(v);
        System.out.println(bfs.getAnswer());
    }
}
