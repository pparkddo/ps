class Solution:

    def climbStairs(self, n: int) -> int:
        dp: list = [1]

        for index in range(1, n):
            if index == 1:
                dp.append(2)
                continue
            dp.append(dp[index-1]+dp[index-2])

        return dp[n-1]
