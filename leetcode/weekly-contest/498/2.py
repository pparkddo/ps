class Solution:
    def firstStableIndex(self, nums: list[int], k: int) -> int:
        n = len(nums)
        maximums, minimums = [nums[0]], [nums[-1]]

        for i in range(1, n):
            maximums.append(max(maximums[-1], nums[i]))
        
        for i in range(n-2, -1, -1):
            minimums.append(min(minimums[-1], nums[i]))
        minimums.reverse()
        
        # print(maximums)
        # print(minimums)

        for i in range(n):
            if maximums[i] - minimums[i] <= k:
                return i

        return -1
