class Solution:

    def hammingWeight(self, n: int) -> int:
        answer: int = 0

        while n:
            # the operation below will remove the rightmost 1 in binary representation of 'n'
            n &= (n - 1)
            answer += 1

        return answer
