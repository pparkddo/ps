class Solution:
    def maxKDistinct(self, nums: List[int], k: int) -> List[int]:
        return sorted(nums, reverse=True)[:k]