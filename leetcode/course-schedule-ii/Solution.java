import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int course = 0; course < numCourses; course++) {
            graph.put(course, new ArrayList<>());
        }

        int[] inDegreeCounts = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
            inDegreeCounts[prerequisite[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int index = 0; index < inDegreeCounts.length; index++) {
            int inDegreeCount = inDegreeCounts[index];
            if (inDegreeCount == 0) {
                queue.add(index);
            }
        }

        int[] answer = new int[numCourses];
        int completeCount = 0;
        while (!queue.isEmpty()) {
            Integer course = queue.poll();
            answer[completeCount] = course;
            completeCount++;
            for (int each : graph.get(course)) {
                inDegreeCounts[each]--;
                if (inDegreeCounts[each] == 0) {
                    queue.add(each);
                }
            }
        }
        
        if (completeCount != numCourses) {
            return new int[] {};
        }
        return answer;
    }
}