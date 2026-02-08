class Solution:
    def dominantIndices(self, nums: List[int]) -> int:
        sums = [nums[-1]]
        averages = [nums[-1]]

        n = len(nums)
        for i in range(1, n):
            sums.append(sums[-1] + nums[n-i-1])
            averages.append(sums[-1] / len(sums))

        sums = sums[::-1]
        averages = averages[::-1]
        
        # print(sums)
        # print(averages)

        answer = 0
        for i in range(n):
            if nums[i] > averages[i]:
                answer += 1
        
        return answer
