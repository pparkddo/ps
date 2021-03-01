import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Node {

    int value;
    int depth;

    Node(int value, int depth) {
        this.value = value;
        this.depth = depth;
    }

    @Override
    public boolean equals(Object obj) {
        Node node = (Node) obj;
        return this.value == node.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.value);
    }

    @Override
    public String toString() {
        return this.value + " " + this.depth;
    }
}

class Solution {

    private void putEdge(Map<Integer, List<Integer>> graph, int from, int to) {
        List<Integer> edges = graph.getOrDefault(from, new ArrayList<>());
        edges.add(to);
        graph.put(from, edges);
    }

    public int solution(int n, int[][] edge) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] each : edge) {
            putEdge(graph, each[0], each[1]);
            putEdge(graph, each[1], each[0]);
        }

        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        queue.add(new Node(1, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (visited.contains(node)) {
                continue;
            }
            visited.add(node);
            for (Integer each : graph.get(node.value)) {
                queue.add(new Node(each, node.depth+1));
            }
        }

        Map<Integer, Integer> answer = new HashMap<>();
        for (Node each : visited) {
            answer.put(each.depth, answer.getOrDefault(each.depth, 0)+1);
        }

        return answer.get(answer.keySet().stream().max(Integer::compare).get());
    }
}
