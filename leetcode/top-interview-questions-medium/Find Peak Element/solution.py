class Solution:

    def findPeakElement(self, nums: list[int]) -> int:
        left: int = 0
        right: int = len(nums) - 1

        while left < right:
            mid: int = (left + right) // 2

            if nums[mid] > nums[mid + 1] and nums[mid] > nums[mid - 1]:
                return mid

            if nums[mid] < nums[mid + 1]:
                left = mid + 1
            else:
                right = mid - 1

        return left
