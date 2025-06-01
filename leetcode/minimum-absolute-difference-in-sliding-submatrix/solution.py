import math
from typing import List


class Solution:

    def minAbsDiff(self, grid: List[List[int]], k: int) -> List[List[int]]:
        answer = []

        for i in range(len(grid)):

            ei = i + (k - 1)
            if ei >= len(grid):
                continue

            for j in range(len(grid[0])):
                ej = j + (k - 1)
                if ej >= len(grid[0]):
                    continue

                s = set()
                for ii in range(i, ei + 1):
                    for jj in range(j, ej + 1):
                        s.add(grid[ii][jj])

                sorted_ = sorted(s)
                m = math.inf
                for idx in range(len(sorted_) - 1):
                    m = min(m, abs(sorted_[idx] - sorted_[idx + 1]))

                if len(answer) < i + 1:
                    answer.append([])

                if len(sorted_) == 1:
                    answer[i].append(0)
                else:
                    answer[i].append(m)

        return answer
