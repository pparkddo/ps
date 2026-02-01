class Solution:
    def countMonobit(self, n: int) -> int:
        answer = 0
        for number in range(n+1):
            if len(set(str(bin(number))[2:])) == 1:
                answer += 1
        return answer
