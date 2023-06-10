# https://leetcode.com/explore/interview/card/top-interview-questions-medium/114/others/822/discuss/776952/Python-BEST-LeetCode-371-Explanation-for-Python
class Solution:

    def getSum(self, a: int, b: int) -> int:
        mask: int = 0xffffffff

        a = a & mask

        while b:
            sum_ = (a ^ b) & mask
            carry = ((a & b) << 1) & mask
            a, b = sum_, carry

        if (a >> 31) & 1:  # if it's negative
            return ~(a ^ mask)  # convert the result to negative form

        return a
