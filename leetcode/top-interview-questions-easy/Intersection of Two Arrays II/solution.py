from typing import List
from collections import Counter


class Solution:

    def intersect(self, nums1: List[int], nums2: List[int]) -> List[int]:
        if len(nums1) > len(nums2):
            return self.intersect(nums2, nums1)

        counter = Counter(nums1)

        answer = []
        for num in nums2:
            if counter[num] > 0:
                answer.append(num)
                counter[num] -= 1
        return answer


if __name__ == '__main__':
    print(Solution().intersect([1, 2, 2, 1], [2, 2]))
    print(Solution().intersect([4, 9, 5], [9, 4, 9, 8, 4]))
    print(Solution().intersect([1, 2, 2, 1], [2]))
