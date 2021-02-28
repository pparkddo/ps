public class Solution2 {
    public int maxProfit(int[] prices) {
        int[] min = new int[prices.length];
        int[] max = new int[prices.length];
        min[0] = prices[0];
        max[prices.length-1] = prices[prices.length-1];
        for (int i = 1; i < prices.length; i++) {
            min[i] = Math.min(min[i-1], prices[i]);
            max[prices.length-(i+1)] = Math.max(max[prices.length-i], prices[prices.length-(i+1)]);
        }
        int answer = 0;
        for (int i = 0; i < prices.length; i++) {
            answer = Math.max(answer, max[i]-min[i]);
        }
        return answer;
    }
}
