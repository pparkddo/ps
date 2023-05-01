class Solution:

    def search(self, nums: list[int], target: int) -> int:
        left: int = 0
        right: int = len(nums) - 1

        while left <= right:
            mid: int = (left + right) // 2
            right_rotated: bool = nums[mid] > nums[right]

            if nums[mid] == target:
                return mid
            elif nums[mid] <= target <= nums[right] and not right_rotated:
                left = mid + 1
            elif (nums[mid] <= target or target <= nums[right]) and right_rotated:
                left = mid + 1
            else:
                right = mid - 1

        return -1
