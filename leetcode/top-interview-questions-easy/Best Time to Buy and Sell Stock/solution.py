class Solution:

    def maxProfit(self, prices: list[int]) -> int:
        dp: list[int] = []

        for price in prices:
            if not dp:
                dp.append(price)
                continue
            dp.append(min(price, dp[-1]))

        answer: int = 0

        for min_, price in zip(dp, prices):
            answer = max(answer, price - min_)

        return answer
