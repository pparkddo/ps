class Solution:

    def uniquePaths(self, m: int, n: int) -> int:
        dp: list[list[int]] = [[0] * n for _ in range(m)]

        for row_index in range(m):
            for column_index in range(n):
                if row_index == 0 or column_index == 0:
                    dp[row_index][column_index] = 1
                    continue
                dp[row_index][column_index] = dp[row_index - 1][column_index] + dp[row_index][column_index - 1]

        return dp[-1][-1]
