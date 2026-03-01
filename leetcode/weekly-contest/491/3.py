from typing import List


class Solution:
    def minimumOR(self, grid: List[List[int]]) -> int:
        ans = 0
        candidates = [row[:] for row in grid]
        
        for bit in range(16, -1, -1):
            mask = 1 << bit
            can_avoid = all(
                any((x & mask) == 0 for x in row)
                for row in candidates
            )
            
            if can_avoid:
                for i in range(len(candidates)):
                    candidates[i] = [x for x in candidates[i] if (x & mask) == 0]
            else:
                ans |= mask
        
        return ans