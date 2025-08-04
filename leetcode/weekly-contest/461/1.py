def check(p, q, n, nums):
    i = 0
    while i < p:
        if nums[i] >= nums[i + 1]:
            return False
        i += 1
    while i < q:
        if nums[i] <= nums[i + 1]:
            return False
        i += 1
    while i < n - 1:
        if nums[i] >= nums[i + 1]:
            return False
        i += 1
    return True


class Solution:

    def isTrionic(self, nums: List[int]) -> bool:
        n = len(nums)
        for p in range(1, n):
            for q in range(p+1, n-1):
                if check(p, q, n, nums):
                    return True
        return False