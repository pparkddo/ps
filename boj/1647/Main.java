import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    private static final int ROOT = -1;

    private static int solution(int n, int m, List<Edge> edges) {
        Collections.sort(edges);

        int[] parents = new int[n+1];
        Arrays.fill(parents, ROOT);

        List<Edge> spanningTreeEdges = new ArrayList<>();
        for (Edge edge : edges) {
            if (spanningTreeEdges.size() == n-1) {
                break;
            }
            if (!union(edge.a, edge.b, parents)) {
                continue;
            }
            spanningTreeEdges.add(edge);
        }

        int sum = spanningTreeEdges.stream().mapToInt(each -> each.cost).sum();
        int largestCostEdge = spanningTreeEdges.get(spanningTreeEdges.size()-1).cost;
        return sum - largestCostEdge;
    }

    private static boolean union(int a, int b, int[] parents) {
        a = find(a, parents);
        b = find(b, parents);

        if (a == b) {
            return false;
        }

        parents[b] = a;
        return true;
    }

    private static int find(int v, int [] parents) {
        if (parents[v] == ROOT) {
            return v;
        }
        return parents[v] = find(parents[v], parents);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String[] each = in.readLine().split(" ");
            int a = Integer.parseInt(each[0]);
            int b = Integer.parseInt(each[1]);
            int cost = Integer.parseInt(each[2]);
            edges.add(new Edge(a, b, cost));
        }
        in.close();
        System.out.println(solution(n, m, edges));
    }
}

class Edge implements Comparable<Edge> {

    int a;
    int b;
    int cost;

    Edge(int a, int b, int cost) {
        this.a = a;
        this.b = b;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(cost, o.cost);
    }

    @Override
    public String toString() {
        return a + " " + b + " " + cost;
    }
}
