def charge(amount, coins):
    dp = [[0 for _ in range(amount + 1)] for _ in range(len(coins) + 1)]
    dp[0][0] = 1

    for c in range(1, len(coins) + 1):
        dp[c][0] = 1
        for a in range(1, amount + 1):
            ci, prev_coin = c - 1, c - 1
            coin = coins[ci]
            dp[c][a] = dp[prev_coin][a]
            if a >= coin:
                dp[c][a] += dp[c][a - coin]

    return dp[len(coins)][amount]


class Solution:

    def findCoins(self, numWays: List[int]) -> List[int]:
        answer = []

        for i, way in enumerate(numWays):
            result = charge(i+1, answer)
            if result + 1 == way:
                answer.append(i+1)
            elif result != way:
                return []

        return answer
