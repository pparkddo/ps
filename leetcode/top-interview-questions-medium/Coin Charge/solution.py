class Solution:

    MAX: int = 2 ** 31 - 1
    CAN_NOT_BE_CHARGED_FOR = -1

    def coinChange(self, coins: list[int], amount: int) -> int:
        dp: list[int] = [self.MAX] * (amount + 1)
        dp[0] = 0

        coins.sort()

        for each_amount in range(1, amount + 1):
            for coin in coins:
                if each_amount >= coin:
                    dp[each_amount] = min(dp[each_amount], dp[each_amount-coin] + 1)

        return dp[-1] if dp[-1] != self.MAX else self.CAN_NOT_BE_CHARGED_FOR


if __name__ == '__main__':
    print(Solution().coinChange([186, 419, 83, 408], 6249))
