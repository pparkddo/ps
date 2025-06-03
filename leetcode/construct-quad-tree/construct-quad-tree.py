"""
# Definition for a QuadTree node.
class Node: def __init__(self, val, isLeaf, topLeft, topRight, bottomLeft, bottomRight):
        self.val = val
        self.isLeaf = isLeaf
        self.topLeft = topLeft
        self.topRight = topRight
        self.bottomLeft = bottomLeft
        self.bottomRight = bottomRight
"""


class Solution:

    def construct(self, grid: List[List[int]]) -> 'Node':

        def divide_and_conquer(grid, r, c, l):
            if l == 1:
                return Node(True if grid[r][c] == 1 else False, True, None, None, None, None)

            tl = divide_and_conquer(grid, r, c, l // 2)
            tr = divide_and_conquer(grid, r, c + l // 2, l // 2)
            bl = divide_and_conquer(grid, r + l // 2, c, l // 2)
            br = divide_and_conquer(grid, r + l // 2, c + l // 2, l // 2)

            if tl.isLeaf and tr.isLeaf and bl.isLeaf and br.isLeaf and tl.val == tr.val == bl.val == br.val:
                return Node(tl.val, True, None, None, None, None)

            return Node(False, False, tl, tr, bl, br)

        return divide_and_conquer(grid, 0, 0, len(grid))




