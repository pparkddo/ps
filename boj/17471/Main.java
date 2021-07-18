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

    private static final Boolean DISTRICT_A = true;
    private static final Boolean DISTRICT_B = false;

    private static int solution(int n, int[] populations, Map<Integer, List<Integer>> graph) {
        Map<Boolean, Set<Integer>> districts = new HashMap<>();
        districts.put(DISTRICT_A, new HashSet<>());
        districts.put(DISTRICT_B, new HashSet<>());
        for (int v = 0; v < n; v++) {
            districts.get(DISTRICT_A).add(v);
        }
        int answer = computeCases(0, districts, n, graph, populations);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private static int computeCases(int depth, Map<Boolean, Set<Integer>> districts, int n,
            Map<Integer, List<Integer>> graph, int[] populations) {
        if (depth == n) {
            return getPopulationGap(n, graph, districts, populations);
        }
        int result = Integer.MAX_VALUE;

        districts.get(DISTRICT_A).remove(depth);
        districts.get(DISTRICT_B).add(depth);
        result = Math.min(result, computeCases(depth+1, districts, n, graph, populations));

        districts.get(DISTRICT_B).remove(depth);
        districts.get(DISTRICT_A).add(depth);
        result = Math.min(result, computeCases(depth+1, districts, n, graph, populations));
        districts.get(DISTRICT_B).add(depth);
        districts.get(DISTRICT_A).remove(depth);

        return result;
    }

    private static int getPopulationGap(int n, Map<Integer, List<Integer>> graph,
            Map<Boolean, Set<Integer>> districts, int[] populations) {
        if (districts.get(DISTRICT_A).isEmpty() || districts.get(DISTRICT_B).isEmpty()) {
            return Integer.MAX_VALUE;
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        int districtACount= 0;
        queue.add(districts.get(DISTRICT_A).stream().findFirst().get());
        while (!queue.isEmpty()) {
            final Integer node = queue.poll();
            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            districtACount += populations[node];
            for (Integer each : graph.get(node)) {
                if (!districts.get(DISTRICT_A).contains(each)) {
                    continue;
                }
                queue.add(each);
            }
        }

        int districtBCount = 0;
        queue.add(districts.get(DISTRICT_B).stream().findFirst().get());
        while (!queue.isEmpty()) {
            final Integer node = queue.poll();
            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            districtBCount += populations[node];
            for (Integer each : graph.get(node)) {
                if (!districts.get(DISTRICT_B).contains(each)) {
                    continue;
                }
                queue.add(each);
            }
        }

        for (int index = 0; index < visited.length; index++) {
            if (!visited[index]) {
                return Integer.MAX_VALUE;
            }
        }

        return Math.abs(districtACount-districtBCount);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        String[] populationString = in.readLine().split(" ");
        int[] populations = new int[n];
        for (int index = 0; index < n; index++) {
            populations[index] = Integer.parseInt(populationString[index]);
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int v = 0; v < n; v++) {
            graph.put(v, new ArrayList<>());
        }
        for (int v = 0; v < n; v++) {
            String[] each = in.readLine().split(" ");
            for (int index = 1; index < each.length; index++) {
                graph.get(v).add(Integer.parseInt(each[index])-1);
            }
        }
        in.close();
        System.out.println(solution(n, populations, graph));
    }
}
