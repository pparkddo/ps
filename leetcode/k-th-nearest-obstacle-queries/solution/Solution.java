import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    public int[] resultsArray(int[][] queries, int k) {
        int[] answer = new int[queries.length];

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int distance = Math.abs(query[0]) + Math.abs(query[1]);

            if (pq.size() < k) {
                pq.add(distance);
                if (pq.size() == k) {
                    answer[i] = pq.peek();
                } else {
                    answer[i] = -1;
                }
                continue;
            }

            if (distance < pq.peek()) {
                pq.poll();
                pq.add(distance);
            }

            answer[i] = pq.peek();
        }

        return answer;
    }
}
