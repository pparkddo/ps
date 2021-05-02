import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    
    private Map<Integer, List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] each : prerequisites) {
            graph.get(each[0]).add(each[1]);
        }
        return graph;
    }
    
    private boolean hasCycle(int course, Map<Integer, List<Integer>> graph, boolean[] visited, boolean[] finished) {
        if (visited[course]) {
            if (!finished[course]) {
                return true;
            }
            return false;
        }
        visited[course] = true;
        for (int each : graph.get(course)) {
            if (hasCycle(each, graph, visited, finished)) {
                return true;
            }
        }
        finished[course] = true;
        return false;
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites);
        boolean[] visited = new boolean[numCourses];
        boolean[] finished = new boolean[numCourses];
        for (int course = 0; course < numCourses; course++) {
            if (visited[course]) {
                continue;
            }
            if (hasCycle(course, graph, visited, finished)) {
                return false;
            }
        }
        return true;
    }
}