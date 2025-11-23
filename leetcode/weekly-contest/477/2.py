class Solution:
    def maxBalancedSubarray(self, nums):
        pre_xor = 0
        pre_cnt = 0
        seen = {(0, 0): -1}
        answer = 0

        for i, num in enumerate(nums):
            pre_xor ^= num
            pre_cnt += 1 if num % 2 == 1 else -1

            state = (pre_xor, pre_cnt)

            if state in seen:
                answer = max(answer, i - seen[state])
            else:
                seen[state] = i

        return answer
