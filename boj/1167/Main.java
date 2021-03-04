import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Node {

    int from;
    int to;
    int distance;

    Node(int from, int to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return this.from + "->" + this.to + "(" + this.distance + ")";
    }
}

class DFS {

    private Map<Integer, List<Node>> tree;
    private Set<Integer> visited = new HashSet<>();
    private int max = Integer.MIN_VALUE;
    private int furthest;

    DFS(Map<Integer, List<Node>> tree) {
        this.tree = tree;
    }

    public int run(int initialVertex) {
        dfs(initialVertex, 0);
        return furthest;
    }

    public int getMax() {
        return max;
    }

    private void dfs(int vertex, int sum) {
        if (visited.contains(vertex)) {
            return;
        }
        if (sum > max) {
            max = sum;
            furthest = vertex;
        }
        visited.add(vertex);
        for (Node node : tree.get(vertex)) {
            dfs(node.to, sum+node.distance);
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int v = Integer.parseInt(in.readLine());
        Map<Integer, List<Node>> tree = new HashMap<>();
        for (int i = 0; i < v; i++) {
            String[] each = in.readLine().split(" ");
            int vertex = Integer.parseInt(each[0]);
            List<Node> nodes = new ArrayList<>();
            int index = 1;
            while (!each[index].equals("-1")) {
                int e = Integer.parseInt(each[index++]);
                int distance = Integer.parseInt(each[index++]);
                nodes.add(new Node(vertex, e, distance));
            }
            tree.put(vertex, nodes);
        }
        in.close();

        int a = new DFS(tree).run(tree.keySet().stream().findAny().get());
        DFS runner = new DFS(tree);
        runner.run(a);
        System.out.println(runner.getMax());
    }
}
