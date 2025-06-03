import math
from collections import deque
from typing import List


def is_valid(r, c, board):
    return 0 <= r < len(board) and 0 <= c < len(board[0])


class Solution:

    def minMoves(self, classroom: List[str], energy: int) -> int:
        board = [list(row) for row in classroom]

        litters = {}

        start = None
        seq = 0
        for i in range(len(board)):
            for j in range(len(board[0])):
                if board[i][j] == "L":
                    if seq == 0:
                        seq = 1
                    litters[(i, j)] = seq
                    seq = seq << 1
                if board[i][j] == "S":
                    start = (i, j)

        # print(litters)

        if not litters:
            return 0

        drs = [(-1, 0), (1, 0), (0, -1), (0, 1)]
        queue = deque()

        tm = (1 << (len(litters))) -1

        queue.append((*start, 0, 0))

        visited = [[[-1 for _ in range(1 << len(litters))] for _ in range(len(board[0]))] for _ in range(len(board))]
        visited[start[0]][start[1]][0] = energy

        while queue:
            r, c, mv, m = queue.popleft()

            e = visited[r][c][m]
            print(r, c, e, m, mv)

            for dr in drs:
                nr, nc = r + dr[0], c + dr[1]
                if not is_valid(nr, nc, board):
                    continue
                if board[nr][nc] == "X":
                    continue
                if e == 0:
                    continue
                nmv, ne, nm = mv + 1, e -1, m
                if board[nr][nc] == "L":
                    nm |= litters[(nr, nc)]
                if board[nr][nc] == "R":
                    ne = energy
                if nm == tm:
                    return nmv
                if ne <= visited[nr][nc][nm]:
                    continue
                visited[nr][nc][nm] = ne
                queue.append((nr, nc, nmv, nm))

        return -1
