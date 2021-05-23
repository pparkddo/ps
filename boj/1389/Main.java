import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Main {

    private static int bfs(int n, int vertex, Map<Integer, Set<Integer>> graph) {
        int[] visited = new int[n+1];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(vertex, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.vertex != vertex && visited[node.vertex] != 0) {
                continue;
            }
            visited[node.vertex] = node.depth;
            for (Integer each : graph.get(node.vertex)) {
                queue.add(new Node(each, node.depth+1));
            }
        }
        visited[vertex] = 0;
        return Arrays.stream(visited).sum();
    }

    private static int solution(int n, int m, Map<Integer, Set<Integer>> graph) {
        int min = Integer.MAX_VALUE;
        int answer = -1;
        for (int vertex = 1; vertex <= n; vertex++) {
            int sum = bfs(n, vertex, graph);
            if (min > sum) {
                min = sum;
                answer = vertex;
            }
        }
        return answer;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int v = 1; v <= n; v++) {
            graph.put(v, new HashSet<>());
        }
        for (int i = 0; i < m; i++) {
            String[] ab = in.readLine().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        in.close();
        System.out.println(solution(n, m, graph));
    }
}

class Node {

    int vertex;
    int depth;

    Node(int vertex, int depth) {
        this.vertex = vertex;
        this.depth = depth;
    }
}
