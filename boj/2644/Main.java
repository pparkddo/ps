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

    private static final int UNREACHABLE = -1;

    private static int solution(int n, int source, int destination, int m, Map<Integer, List<Integer>> graph) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(source, 0));

        int answer = UNREACHABLE;
        boolean[] visited = new boolean[n+1];
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int value = node.value;
            int count = node.count;
            if (value == destination) {
                answer = count;
                break;
            }
            for (Integer each : graph.get(value)) {
                if (visited[each]) {
                    continue;
                }
                queue.add(new Node(each, count+1));
                visited[each] = true;
            }
        }

        return answer;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        String[] sd = in.readLine().split(" ");
        int source = Integer.parseInt(sd[0]);
        int destination = Integer.parseInt(sd[1]);
        int m = Integer.parseInt(in.readLine());
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            String[] xy = in.readLine().split(" ");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        in.close();
        System.out.println(solution(n, source, destination, m, graph));
    }
}

class Node {

    int value;
    int count;

    Node(int value, int count) {
        this.value = value;
        this.count = count;
    }
}
