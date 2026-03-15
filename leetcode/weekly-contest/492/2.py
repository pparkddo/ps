class Solution:
    def smallestBalancedIndex(self, nums: list[int]) -> int:
        MAX_VAL = 10**14 + 1
        n = len(nums)
        p = [nums[-1]] * n

        for i in range(n-2, -1, -1):
            if p[i+1] > MAX_VAL:
                p[i] = MAX_VAL
            else:
                p[i] = p[i+1] * nums[i]
        # print(p)

        s = 0
        for i in range(len(nums)):
            sum_ = s if i != 0 else 0
            prod = p[i+1] if i != len(nums) - 1 else 1
            # print(sum_, prod)
            s += nums[i]
            if sum_ == prod:
                return i
            

        return -1