class Solution {

    public int findKthLargest(int[] nums, int k) {
        int max = 20001;
        int[] container = new int[max];
        int offset = 10000;
        for (int each : nums) {
            container[each+offset]++;
        }

        int answer = -1;
        int count = 0;
        for (int index = container.length-1; index >= 0; index--) {
            count += container[index];
            if (count >= k) {
                answer = index - offset;
                break;
            }
        }

        return answer;
    }
}