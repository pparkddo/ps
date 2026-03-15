from typing import List


class Solution:
    def longestArithmetic(self, nums: List[int]) -> int:
        n = len(nums)
        diff = []
        for i in range(1, n):
            diff.append(nums[i] - nums[i-1])
        # print(diff)

        def compute(diff):
            dn = n - 1
            result = 1
            left, right = 0, 0
            while right < dn - 1:
                while right < dn - 1 and diff[left] == diff[right+1]:
                    right += 1
                expanded = False
                change_point = right
                if right < dn - 1:
                    d = diff[right+1] - diff[right]
                    right += 1
                    if right < dn - 1:
                        if diff[left] == diff[right+1] + d:
                            right += 1
                            expanded = True
                while right < dn - 1 and expanded and diff[left] == diff[right+1]:
                    right += 1
                # print(left, right, right-left+1)
                result = max(result, right - left + 1)
                if expanded:
                    left = change_point + 1
                    right = left
                else:
                    left = right
            return result

        answer = 1
        answer = max(answer, compute(diff))
        # print("---")
        diff.reverse()
        answer = max(answer, compute(diff))

        return answer + 1