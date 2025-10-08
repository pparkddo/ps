class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        if not prices:
            return 0

        n = len(prices)
        t = 2 + 1

        dp = [[0] * n for _ in range(t)]

        for i in range(1, t):
            min_ = prices[0]
            for j in range(1, n):
                min_ = min(min_, prices[j-1] - dp[i-1][j-1])
                max_profit = prices[j] - min_
                dp[i][j] = max(dp[i][j-1], max_profit)

        return dp[-1][-1]
