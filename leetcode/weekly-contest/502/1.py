class Solution:
    def isAdjacentDiffAtMostTwo(self, s: str) -> bool:
        for i in range(1, len(s)):
            if abs(int(s[i-1]) - int(s[i])) > 2:
                return False
        return True
