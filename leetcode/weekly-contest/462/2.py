class Solution:
    def sortPermutation(self, nums: List[int]) -> int:
        need_to_be_sorted = [num for i, num in enumerate(nums) if i != num]
        # print(need_to_be_sorted)

        if not need_to_be_sorted:
            return 0

        answer = need_to_be_sorted[0]
        for i in range(len(need_to_be_sorted)):
            answer &= need_to_be_sorted[i]

        return answer
