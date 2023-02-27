class Solution:

    def merge(self, nums1: list[int], m: int, nums2: list[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        temp: list = []

        left_index, right_index = 0, 0

        while left_index < m and right_index < n:
            left: int = nums1[left_index]
            right: int = nums2[right_index]

            if left < right:
                temp.append(left)
                left_index += 1
            else:
                temp.append(right)
                right_index += 1

        for index in range(left_index, m):
            temp.append(nums1[index])

        for index in range(right_index, n):
            temp.append(nums2[index])

        for index, num in enumerate(temp):
            nums1[index] = num
