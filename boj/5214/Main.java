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

    private static int solution(int n, int k, int m, Map<Integer, List<Integer>> graph) {
        int source = 1;
        int destination = n;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(source, 0));

        boolean[] visited = new boolean[n+m+1];
        visited[source] = true;

        int minimumCount = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int stationId = node.id;
            int count = node.count;
            if (stationId == destination) {
                minimumCount = count;
                break;
            }
            for (Integer each : graph.get(stationId)) {
                if (visited[each]) {
                    continue;
                }
                queue.add(new Node(each, count+1));
                visited[each] = true;
            }
        }

        if (n != 1 && minimumCount == Integer.MAX_VALUE) {
            return -1;
        }

        return minimumCount / 2 + 1;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nkm = in.readLine().split(" ");
        int n = Integer.parseInt(nkm[0]);
        int k = Integer.parseInt(nkm[1]);
        int m = Integer.parseInt(nkm[2]);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int stationId = 1; stationId <= n; stationId++) {
            graph.put(stationId, new ArrayList<>());
        }
        for (int index = 1; index <= m; index++) {
            int hyperTubeId = index + n;
            graph.put(hyperTubeId, new ArrayList<>());
            String[] each = in.readLine().split(" ");
            for (String stationId : each) {
                int id = Integer.parseInt(stationId);
                graph.get(hyperTubeId).add(id);
                graph.get(id).add(hyperTubeId);
            }
        }
        in.close();
        System.out.println(solution(n, k, m, graph));
    }
}

class Node {

    int id;
    int count;

    Node(int id, int count) {
        this.id = id;
        this.count = count;
    }
}