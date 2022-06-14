class Solution {

    fun maxProfit(prices: IntArray): Int {
        var answer = 0

        for (index in 0 until prices.lastIndex) {
            if (prices[index] < prices[index+1]) {
                answer += (prices[index+1] - prices[index])
            }
        }

        return answer
    }
}
