from typing import List


class Solution:

    def checkEqualPartitions(self, nums: List[int], target: int) -> bool:

        def traverse(index, v, other_v, visited, nums, target):

            for i in range(index + 1, len(nums)):
                if i in visited:
                    continue

                t = v * nums[i]
                other_t = other_v // nums[i]

                if t == target and other_t == target:
                    return True
                elif t >= target:
                    continue

                visited.add(i)
                if traverse(i, t, other_t, visited, nums, target):
                    return True
                visited.remove(i)

            return False

        other_v = 1
        for each in nums:
            other_v = each * other_v

        return traverse(-1, 1, other_v, set(), nums, target)
