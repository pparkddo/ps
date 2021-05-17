import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    private static final int ROOT = -1;

    private static double getDistance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x-b.x, 2) + Math.pow(a.y-b.y, 2));
    }

    private static List<Edge> getEdges(int n, List<Point> points) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                Point a = points.get(i);
                Point b = points.get(j);
                edges.add(new Edge(a.id, b.id, getDistance(a, b)));
            }
        }
        return edges;
    }

    private static String solution(int n, int m, List<Point> points, List<ConnectedEdge> connectedEdges) {
        List<Edge> edges = getEdges(n, points);
        Collections.sort(edges);

        int[] parents = new int[n];
        Arrays.fill(parents, ROOT);

        for (ConnectedEdge each : connectedEdges) {
            union(parents, each.a, each.b);
        }

        double answer = 0;
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (!union(parents, edge.a, edge.b)) {
                continue;
            }
            answer += edge.cost;
        }
        return String.format("%.2f", answer);
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

    private static int find(int[] parents, int id) {
        if (parents[id] == ROOT) {
            return id;
        }
        return parents[id] = find(parents, parents[id]);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] xy = in.readLine().split(" ");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            points.add(new Point(i, x, y));
        }
        List<ConnectedEdge> connectedEdges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String[] ab = in.readLine().split(" ");
            int a = Integer.parseInt(ab[0]) - 1;
            int b = Integer.parseInt(ab[1]) - 1;
            connectedEdges.add(new ConnectedEdge(a, b));
        }
        in.close();
        System.out.println(solution(n, m, points, connectedEdges));
    }
}

class Point {

    int id;
    int x;
    int y; 

    Point(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
}

class ConnectedEdge {

    int a;
    int b;

    ConnectedEdge(int a, int b) {
        this.a = a;
        this.b = b;
    }
}

class Edge implements Comparable<Edge> {

    int a;
    int b;
    double cost;

    Edge(int a, int b, double cost) {
        this.a = a;
        this.b = b;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(cost, o.cost);
    }

    @Override
    public String toString() {
        return "Edge(" + a + ", " + b + "," + cost + ")";
    }
}