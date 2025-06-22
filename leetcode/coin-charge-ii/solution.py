class Solution:

    def change(self, amount: int, coins: List[int]) -> int:
        dp = [[0 for _ in range(amount + 1)] for _ in range(len(coins) + 1)]
        dp[0][0] = 1

        for c in range(1, len(coins)+1):
            dp[c][0] = 1
            for a in range(1, amount+1):
                ci, prev_coin = c - 1, c - 1
                coin = coins[ci]
                dp[c][a] = dp[prev_coin][a]
                if a >= coin:
                    dp[c][a] += dp[c][a-coin]

        # print("\n".join(map(str, dp)))

        return dp[len(coins)][amount]

