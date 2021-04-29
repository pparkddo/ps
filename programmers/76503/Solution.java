import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

class Solution {

    private Map<Integer, List<Integer>> buildTree(int[][] edges) {
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            List<Integer> aEdges = tree.getOrDefault(a, new ArrayList<>());
            List<Integer> bEdges = tree.getOrDefault(b, new ArrayList<>());
            aEdges.add(b);
            bEdges.add(a);
            tree.put(a, aEdges);
            tree.put(b, bEdges);
        }
        return tree;
    }

    private long dfs(Map<Integer, List<Integer>> tree, long[] weights) {
        long answer = 0;

        Stack<Node> stack = new Stack<>();
        stack.push(new Node(0, 0));

        boolean[] visited = new boolean[weights.length];
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            int current = node.current;
            int previous = node.previous;

            if (visited[current]) {
                answer += Math.abs(weights[current]);
                weights[previous] += weights[current];
                weights[current] = 0;
                continue;
            }

            stack.push(new Node(current, previous));
            visited[current] = true;

            for (int each : tree.getOrDefault(current, new ArrayList<>())) {
                if (visited[each]) {
                    continue;
                }
                stack.push(new Node(each, current));
            }
        }

        return answer;
    }

    public long solution(int[] a, int[][] edges) {
        long[] weights = Arrays.stream(a).mapToLong(Long::valueOf).toArray();
        if (Arrays.stream(weights).sum() != 0) {
            return -1;
        }
        Map<Integer, List<Integer>> tree = buildTree(edges);
        return dfs(tree, weights);
    }
}

class Node {
    
    int current;
    int previous;

    Node(int current, int previous) {
        this.current = current;
        this.previous = previous;
    }
}
