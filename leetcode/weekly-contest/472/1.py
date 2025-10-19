class Solution:
    def missingMultiple(self, nums: List[int], k: int) -> int:
        nums = set(nums)
        for i in range(1, 102):
            m = k * i
            if m not in nums:
                return m
        return -1
