from collections import defaultdict


class Solution:

    def twoSum(self, nums: list[int], target: int) -> list[int]:
        counter: dict[int, list[int]] = defaultdict(list)

        for index, num in enumerate(nums):
            counter[num].append(index)

        for index, num in enumerate(nums):
            difference: int = target - num

            # check if there is a difference in the counter dict
            if num != difference and counter[difference]:
                if counter[difference]:
                    return [index, counter[difference][0]]

            # note that there are some duplicate numbers
            # So we should check there are more than two indices in the counter value list
            elif num == difference and len(counter[difference]) >= 2:
                return counter[difference][:2]


if __name__ == '__main__':
    print(Solution().twoSum([2, 7, 11, 15], 9))
    print(Solution().twoSum([3, 2, 4], 6))
    print(Solution().twoSum([3, 3], 6))
    print(Solution().twoSum([2, 7, 11, 15, 7], 9))
