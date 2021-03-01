import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

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
    public int compareTo(Edge edge) {
        return Integer.compare(this.cost, edge.cost);
    }
}

public class Main {

    private static final int ROOT = -1;

    private static int find(int[] parents, int n) {
        if (parents[n] == ROOT) {
            return n;
        }
        return parents[n] = find(parents, parents[n]);
    }

    private static boolean union(int[] parents, int a, int b) {
        a = find(parents, a);
        b = find(parents, b);
        if (a == b) {
            return false;
        }
        parents[b] = a;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] ve = in.readLine().split(" ");
        int v = Integer.parseInt(ve[0]);
        int e = Integer.parseInt(ve[1]);
        Edge[] edges = new Edge[e];
        for (int i = 0; i < e; i++) {
            String[] each = in.readLine().split(" ");
            int a = Integer.parseInt(each[0])-1;
            int b = Integer.parseInt(each[1])-1;
            int cost = Integer.parseInt(each[2]);
            edges[i] = new Edge(a, b, cost);
        }
        Arrays.sort(edges);
        int edgeCount = 0;
        int answer = 0;
        int[] parents = new int[v];
        Arrays.fill(parents, ROOT);
        for (int i = 0; i < e; i++) {
            if (edgeCount == v-1) {
                break;
            }
            Edge edge = edges[i];
            if (!union(parents, edge.a, edge.b)) {
                continue;
            }
            answer += edge.cost;
            edgeCount++;
        }
        in.close();
        System.out.println(answer);
    }
}
