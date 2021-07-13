import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Main {

    private static final int MAX_RELATION_DISTANCE = 2;

    private static int solution(int n, int m, Map<Integer, List<Integer>> graph) {
        int initialVertex = 1;
        return getInviteCount(initialVertex, n, graph);
    }

    private static int getInviteCount(int initialVertex, int n, Map<Integer, List<Integer>> graph) {
        boolean[] visited = new boolean[n+1];
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(initialVertex, 0));

        int count = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            final int value = node.value;
            final int distance = node.distance;
            if (visited[value]) {
                continue;
            }
            if (distance > MAX_RELATION_DISTANCE) {
                continue;
            }
            if (value != initialVertex) {
                count++;
            }
            visited[value] = true;
            for (Integer each : graph.get(value)) {
                queue.add(new Node(each, distance+1));
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int m = Integer.parseInt(in.readLine());
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int v = 1; v <= n; v++) {
            graph.put(v, new ArrayList<>());
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

    int value;
    int distance;

    Node(int value, int distance) {
        this.value = value;
        this.distance = distance;
    }
}
