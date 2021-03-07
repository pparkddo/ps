import java.util.Collections;
import java.util.PriorityQueue;

class DoublePriorityQueue {

    int size = 0;
    PriorityQueue<Integer> minQueue = new PriorityQueue<>();
    PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());

    public void add(int number) {
        if (isEmpty()) {
            clear();
        }
        size++;
        minQueue.add(number);
        maxQueue.add(number);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void clear() {
        this.minQueue.clear();
        this.maxQueue.clear();
    }

    public int pollMin() {
        if (isEmpty()) {
            clear();
            return 0;
        }
        size--;
        return minQueue.poll();
    }

    public int pollMax() {
        if (isEmpty()) {
            clear();
            return 0;
        }
        size--;
        return maxQueue.poll();
    }
}

class Solution {
    public int[] solution(String[] operations) {
        DoublePriorityQueue doublePriorityQueue = new DoublePriorityQueue();
        for (String operation : operations) {
            String[] command = operation.split(" ");
            String c = command[0];
            int parameter = Integer.parseInt(command[1]);
            if (c.equals("I")) {
                doublePriorityQueue.add(parameter);
            }
            if (c.equals("D") && parameter == 1) {
                doublePriorityQueue.pollMax();
            }
            if (c.equals("D") && parameter == -1) {
                doublePriorityQueue.pollMin();
            }
        }
        int[] answer = new int[2];
        answer[0] = doublePriorityQueue.pollMax();
        answer[1] = doublePriorityQueue.pollMin();
        return answer;
    }
}
