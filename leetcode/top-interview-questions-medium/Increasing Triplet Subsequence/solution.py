class Solution:

    def increasingTriplet(self, nums: list[int]) -> bool:
        max_: int = 2 ** 31
        first: int = max_
        second: int = max_

        for num in nums:
            if num <= first:
                first = num
            elif num <= second:
                second = num
            else:
                return True

        return False
