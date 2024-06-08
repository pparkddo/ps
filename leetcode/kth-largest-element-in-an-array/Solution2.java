import java.util.PriorityQueue;

class Solution {

    public int findKthLargest(int[] nums, int k) {
        var pq = new PriorityQueue<Integer>((a, b) -> b - a);
        for (var num : nums) {
            pq.add(num);
        }

        while (k-- > 1) {
            pq.poll();
        }

        return pq.poll();
    }
}
