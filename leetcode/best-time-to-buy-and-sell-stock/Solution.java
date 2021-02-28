class Solution {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            if (price < min) {
                min = price;
                continue;
            }
            max = Math.max(max, price-min);
        }
        return max;
    }
}