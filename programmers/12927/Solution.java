import java.util.Collections;
import java.util.PriorityQueue;

class Solution {

    private PriorityQueue<Integer> getPriorityQueue(int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) {
            pq.add(work);
        }
        return pq;
    }

    private void subtract(PriorityQueue<Integer> pq, int n) {
        while (n-- > 0) {
            int work = pq.poll();
            pq.add(work != 0 ? work-1 : 0);
        }
    }

    private long getResult(PriorityQueue<Integer> pq) {
        return pq.stream()
                 .mapToLong(Long::valueOf)
                 .map(each -> each*each)
                 .sum();
    }

    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = getPriorityQueue(works);
        subtract(pq, n);
        return getResult(pq);
    }
}
