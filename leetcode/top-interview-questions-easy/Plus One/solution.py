from typing import List


class Solution:

    def plusOne(self, digits: List[int]) -> List[int]:
        has_carry = self._plus_one(digits, -1)

        for index in range(len(digits)-2, -1, -1):
            if has_carry:
                has_carry = self._plus_one(digits, index)
            else:
                break

        if has_carry:
            return [1] + digits

        return digits

    def _plus_one(self, digits: List[int], index) -> bool:
        digits[index] += 1

        if digits[index] == 10:
            digits[index] = 0
            return True

        return False


if __name__ == '__main__':
    print(Solution().plusOne([1, 2, 3,]))
    print(Solution().plusOne([4, 3, 2, 1]))
    print(Solution().plusOne([4, 9, 9, 9]))
    print(Solution().plusOne([1]))
    print(Solution().plusOne([9]))
    print(Solution().plusOne([9]))
