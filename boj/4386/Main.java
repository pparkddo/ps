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

    private static Edge getEdge(Point a, Point b) {
        return new Edge(a.id, b.id, getDistance(a, b));
    }

    private static void dfs(int start, int depth, List<Edge> edges, List<Point> points, int[] selected) {
        if (depth == 2) {
            Point a = points.get(selected[0]);
            Point b = points.get(selected[1]);
            edges.add(getEdge(a, b));
            return;
        }
        for (int index = start; index < points.size(); index++) {
            selected[depth] = index;
            dfs(index+1, depth+1, edges, points, selected);
        }
    }

    private static String solution(int n, List<Point> points) {
        List<Edge> edges = new ArrayList<>();
        int[] selected = new int[2];
        dfs(0, 0, edges, points, selected);
        Collections.sort(edges);

        int[] parents = new int[n];
        Arrays.fill(parents, ROOT);

        double answer = 0;
        int edgeCount = 0;
        for (int i = 0; i < edges.size(); i++) {
            if (edgeCount == n-1) {
                break;
            }
            Edge edge = edges.get(i);
            if (!union(parents, edge.a, edge.b)) {
                continue;
            }
            answer += edge.cost;
            edgeCount++;
        }
        return String.format("%.2f", answer);
    }

    private static int find(int[] parents, int id) {
        if (parents[id] == ROOT) {
            return id;
        }
        return parents[id] = find(parents, parents[id]);
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
        int n = Integer.parseInt(in.readLine());
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] xy = in.readLine().split(" ");
            double x = Double.parseDouble(xy[0]);
            double y = Double.parseDouble(xy[1]);
            points.add(new Point(i, x, y));
        }
        in.close();
        System.out.println(solution(n, points));
    }
}

class Point {
    
    int id;
    double x;
    double y;

    Point(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
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
        return "Edge(" + a + ", " + b + ", " + cost + ")";
    }
}
