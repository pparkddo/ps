from collections import deque


class Solution:
    def rotateElements(self, nums: List[int], k: int) -> List[int]:
        n = len(nums)
        p_indexes = []
        p_values = deque()
        for i, num in enumerate(nums):
            if num >= 0:
                p_indexes.append(i)
                p_values.append(num)
                
        # print(p_indexes)
        # print(p_values)

        while k and p_values:
            p_values.append(p_values.popleft())
            k -= 1
            
        # print(p_values)

        for i in p_indexes:
            nums[i] = p_values.popleft()

        return nums
