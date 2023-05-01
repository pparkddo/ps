import random


class Solution:

    def findKthLargest(self, nums: list[int], k: int) -> int:
        if not nums:
            return -1

        pivot: int = random.choice(nums)

        left: list[int] = [x for x in nums if x > pivot]
        mid: list[int] = [x for x in nums if x == pivot]
        right: list[int] = [x for x in nums if x < pivot]

        if k <= len(left):
            return self.findKthLargest(left, k)
        elif k > len(left) + len(mid):
            return self.findKthLargest(right, k - len(left) - len(mid))
        else:
            return mid[0]
