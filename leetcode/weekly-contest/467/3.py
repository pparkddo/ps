class Solution:
    def subsequenceSumAfterCapping(self, nums: List[int], k: int) -> List[bool]:
        nums.sort()
        n  = len(nums)
        dp = 1
        answer = [False] * n
        mask = (1 << k + 1) - 1

        i = 0
        for x in range(1, n + 1):
            while i < n and nums[i] <= x:
                dp |= (dp << nums[i]) & mask
                i += 1
            v = max(k % n, k - (n - i) * x)
            for j in range(v, k+1, x):
                if dp & (1 << j):
                    answer[i - 1] = True
                    break

        return True