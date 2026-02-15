from collections import Counter, defaultdict


class Solution:
    def firstUniqueFreq(self, nums: List[int]) -> int:
        counter = Counter(nums)
        num_by_count = defaultdict(list)
        for k, v in counter.items():
            num_by_count[v].append(k)
        # print(num_by_count)

        unique_nums = {nums[0] for count, nums in num_by_count.items() if len(nums) == 1}
        # print(unique_nums)

        if not unique_nums:
            return -1

        for num in nums:
            if num in unique_nums:
                return num
        