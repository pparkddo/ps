import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        var pq = new PriorityQueue<Node>();

        for (int num : nums1) {
            pq.add(new Node(num, nums2[0], 0));
        }

        var answer = new ArrayList<List<Integer>>();
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            var node = pq.poll();
            int a = node.a();
            int b = node.b();
            int indexOfNum2 = node.indexOfNum2();
            answer.add(List.of(a, b));
            int nextIndexOfNum2 = indexOfNum2 + 1;
            if (nextIndexOfNum2 < nums2.length) {
                pq.add(new Node(a, nums2[nextIndexOfNum2], nextIndexOfNum2));
            }
        }

        return answer;
    }
}

record Node(int a, int b, int indexOfNum2) implements Comparable<Node> {

    @Override
    public int compareTo(Node o) {
        return (this.a + this.b) - (o.a + o.b);
    }
}