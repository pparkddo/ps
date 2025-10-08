INT_MIN = -1e7


class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        def helper(l, r):
            if l > r:
                return INT_MIN
            if l == r:
                return nums[l]

            mid = (l + r) // 2
            lmax = helper(l, mid-1)
            rmax = helper(mid+1, r)

            sum_, ml = 0, 0
            for i in range(mid-1, l-1, -1):
                sum_ += nums[i]
                ml = max(ml, sum_)

            sum_, mr = 0, 0
            for i in range(mid+1, r+1):
                sum_ += nums[i]
                mr = max(mr, sum_)

            return max(max(lmax, rmax), ml + nums[mid] + mr)
        return helper(0, len(nums)-1)
