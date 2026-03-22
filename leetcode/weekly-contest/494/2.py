class Solution:
    def uniformArray(self, nums1: list[int]) -> bool:
        nums1.sort()
        # print(nums1)

        if nums1[0] % 2 == 1:
            return True
        else:
            return len([num for num in nums1 if num % 2 == 1]) == 0
