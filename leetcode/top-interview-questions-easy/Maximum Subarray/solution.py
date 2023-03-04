class Solution:

    def maxSubArray(self, nums: list[int]) -> int:
        answer: int = nums[0]
        sum_of_subarray: int = nums[0]

        for num in nums[1:]:
            if sum_of_subarray > 0:
                sum_of_subarray = sum_of_subarray + num
            else:
                sum_of_subarray = num
            answer = max(answer, sum_of_subarray)

        return answer
