class Solution:

    def rob(self, nums: list[int]) -> int:
        dp: list[list[int]] = []

        for index, num in enumerate(nums):
            if index == 0:
                dp.append([0, num])
                continue

            if index % 2 == 0:
                dp.append([dp[-1][0], dp[-1][1] + num])
            else:
                dp.append([dp[-1][0] + num, dp[-1][1]])

            dp[-1][0] = max(dp[-1][0], dp[-2][1])
            dp[-1][1] = max(dp[-1][1], dp[-2][0])

        return max(dp[-1])
