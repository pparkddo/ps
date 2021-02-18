import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<Integer>());
        }
        int edgeCount = Integer.parseInt(in.readLine());
        for (int i = 0; i < edgeCount; i++) {
            String[] each = in.readLine().split(" ");
            int a = Integer.parseInt(each[0]);
            int b = Integer.parseInt(each[1]);
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        in.close();

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(1);
        while (!queue.isEmpty()) {
            int computer = queue.poll();
            if (visited.contains(computer)) {
                continue;
            }
            visited.add(computer);
            for (Integer each : graph.get(computer)) {
                queue.add(each);
            }
        }
        System.out.println(visited.size()-1);
    }
}
