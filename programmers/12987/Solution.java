import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {

    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int number : B) {
            priorityQueue.add(number);
        }
        int answer = 0;
        int index = 0;
        while (!priorityQueue.isEmpty()) {
            if (A[index] < priorityQueue.poll()) {
                answer++;
                index++;
            }
        }
        return answer;
    }
}
