class Solution:
    def countKthRoots(self, l: int, r: int, k: int) -> int:
        answer = 0

        if k == 1:
            return r - l + 1
        
        v = 0
        while v ** k <= r:
            if l <= v ** k <= r:
                answer += 1
            v += 1

        return answer
