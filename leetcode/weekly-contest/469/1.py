class Solution:
    def decimalRepresentation(self, n: int) -> List[int]:
        answer = []

        i = 0
        while n:
            v = (n % 10) * (10 ** i)
            if v != 0:
                answer.append(v)
            i += 1
            n //= 10

        return sorted(answer, reverse=True)