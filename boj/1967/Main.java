import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {

    private int from;
    private int to;
    private int cost;

    Node(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return this.from + " " + this.to + " " + this.cost;
    }
}

class DFS {

    private Map<Integer, List<Node>> tree;
    private int furthest;
    private int max = Integer.MIN_VALUE;
    private boolean[] visited;

    DFS(Map<Integer, List<Node>> tree) {
        this.tree = tree;
        this.visited = new boolean[tree.size()+1];
    }

    public int getFurthest() {
        return furthest;
    }

    public int getMax() {
        return max;
    }

    public void run(int initial) {
        dfs(initial, 0);
    }

    private void dfs(int vertex, int sum) {
        if (visited[vertex]) {
            return;
        }
        visited[vertex] = true;
        if (max < sum) {
            max = sum;
            furthest = vertex;
        }
        for (Node node : tree.get(vertex)) {
            dfs(node.getTo(), sum+node.getCost());
        }
    }
}

public class Main {

    private static final int ROOT = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        if (n == 1) {
            System.out.println(0);
            return;
        }

        Map<Integer, List<Node>> tree = new HashMap<>();
        for (int i = 0; i < n-1; i++) {
            String[] each = in.readLine().split(" ");
            int a = Integer.parseInt(each[0]);
            int b = Integer.parseInt(each[1]);
            int cost = Integer.parseInt(each[2]);
            List<Node> as = tree.getOrDefault(a, new ArrayList<>());
            List<Node> bs = tree.getOrDefault(b, new ArrayList<>());
            as.add(new Node(a, b, cost));
            bs.add(new Node(b, a, cost));
            tree.put(a, as);
            tree.put(b, bs);
        }
        in.close();

        DFS firstSearchRunner = new DFS(tree);
        firstSearchRunner.run(ROOT);

        DFS secondSearchRunner = new DFS(tree);
        secondSearchRunner.run(firstSearchRunner.getFurthest());

        System.out.println(secondSearchRunner.getMax());
    }
}
