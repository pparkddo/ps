# find the longest palindrome problem? -> x (can remove one char)
# n(2500) ** 2 -> all cases 6,250,000 (not feasible) 
# (X) backtracking/memoization? -> might not be feasible (worst case -> n ** 2) => it works...
# palidrome - 1 = almost palindrom
# two pointer? -> 

class Solution:
    def almostPalindromic(self, s: str) -> int:
        n = len(s)
        if n <= 2:
            return n

        def expand(l: int, r: int) -> tuple[int, int]:
            while l >= 0 and r < n and s[l] == s[r]:
                l -= 1
                r += 1
            return l, r  # palindrome is s[l+1:r]

        result = 1
        for i in range(n):
            # Check two centers: odd (i,i) and even (i,i+1)
            for l0, r0 in ((i, i), (i, i + 1)):
                # Expand the palindrome first
                l, r = expand(l0, r0)
                result = max(result, r - l)  # Length is r - l - 1, and we add 1 as skipped symbol

                # Skip one left
                l1, r1 = expand(l - 1, r)
                result = max(result, r1 - l1 - 1)

                # Skip one right
                l2, r2 = expand(l, r + 1)
                result = max(result, r2 - l2 - 1)
                
                # Early stop: substring can't be bigger than n
                if result >= n:
                    return n

        return result