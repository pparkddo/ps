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

        int answer = 0;
        int edgeCount = 0;
        for (Edge edge : edges) {
            if (edgeCount == n-1) {
                break;
            }
            if (!union(edge.from, edge.to, parents)) {
                continue;
            }
            answer += edge.cost;
            edgeCount++;
        }

        return answer;
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

    private static int find(int n, int[] parents) {
        int parent = parents[n];
        if (parent == ROOT) {
            return n;
        }
        return parents[n] = find(parent, parents);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int m = Integer.parseInt(in.readLine());
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String[] each = in.readLine().split(" ");
            int from = Integer.parseInt(each[0]);
            int to = Integer.parseInt(each[1]);
            int cost = Integer.parseInt(each[2]);
            edges.add(new Edge(from, to, cost));
        }
        in.close();
        System.out.println(solution(n, m, edges));
    }
}

class Edge implements Comparable<Edge> {

    int from;
    int to;
    int cost;

    Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(cost, o.cost);
    }
}
