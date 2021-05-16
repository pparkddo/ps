import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static boolean hasCycle(int vertex, int previous, Map<Integer, List<Integer>> graph, boolean[] visited, boolean[] finished) {
        if (visited[vertex]) {
            if (!finished[vertex]) {
                return true;
            }
            return false;
        }
        visited[vertex] = true;
        for (Integer each : graph.get(vertex)) {
            if (each == previous) {
                continue;
            }
            if (hasCycle(each, vertex, graph, visited, finished)) {
                return true;
            }
        }
        finished[vertex] = true;
        return false;
    }

    private static String solution(int testCase, int n, int m, Map<Integer, List<Integer>> graph) {
        int count = 0;
        boolean[] visited = new boolean[n+1];
        boolean[] finished = new boolean[n+1];
        for (int vertex = 1; vertex <= n; vertex++) {
            if (visited[vertex]) {
                continue;
            }
            if (hasCycle(vertex, -1, graph, visited, finished)) {
                continue;
            }
            count++;
        }
        return getAnswerMessage(testCase, count);
    }

    private static String getAnswerMessage(int testCase, int count) {
        StringBuilder answer = new StringBuilder();
        answer.append("Case ").append(testCase).append(": ");
        if (count == 0) {
            answer.append("No trees.");
        }
        else if (count == 1) {
            answer.append("There is one tree.");
        }
        else {
            answer.append("A forest of ").append(count).append(" trees.");
        }
        return answer.toString();
    }

    private static boolean isExitFlag(int n, int m) {
        return n == 0 && m == 0;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answers = new StringBuilder();
        int testCase = 1;
        while (true) {
            String[] nm = in.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);
            if (isExitFlag(n, m)) {
                break;
            }
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for (int v = 1; v <= n; v++) {
                graph.put(v, new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                String each[] = in.readLine().split(" ");
                int a = Integer.parseInt(each[0]);
                int b = Integer.parseInt(each[1]);
                graph.get(a).add(b);
                graph.get(b).add(a);
            }
            answers.append(solution(testCase++, n, m, graph)).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}
