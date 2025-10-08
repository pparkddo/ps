class Solution:
    def zigZagArrays(self, n: int, l: int, r: int) -> int:
        r -= l
        dp = [1] * (r + 1)
        mod = (10 ** 9) + 7

        for i in range(1, n):
            ps = 0
            increasing = i & 1
            if increasing:
                for v in range(r + 1):
                    t = ps + dp[v]
                    dp[v] = ps % mod
                    ps = t % mod
            else:
                for v in range(r, -1, -1):
                    t = ps + dp[v]
                    dp[v] = ps % mod
                    ps = t % mod

        return sum(dp) * 2 % mod
