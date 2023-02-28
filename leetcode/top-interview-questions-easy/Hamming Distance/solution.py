class Solution:

    def hammingDistance(self, x: int, y: int) -> int:
        x: int = x ^ y

        answer: int = 0

        while x:
            x &= (x - 1)
            answer += 1

        return answer

