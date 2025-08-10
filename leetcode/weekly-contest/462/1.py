class Solution:
    def reverseSubmatrix(self, grid: List[List[int]], x: int, y: int, k: int) -> List[List[int]]:
        for r in range(x, x+k):
            rr = (x + k - 1) - (r - x)

            if r >= rr:
                break

            for c in range(y, y+k):
                grid[r][c], grid[rr][c] = grid[rr][c], grid[r][c]

        return grid