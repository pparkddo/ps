# Fail
from itertools import chain
from collections import Counter


WHITE = "W"
BLACK = "B"
BLANK = "."
SIZE = 6


def print_board(board):
    for row in board:
        print("".join(row))


def next_step(r, c, wise):
    steps = {
        1: (-1, -1),
        2: (-1, 0),
        3: (-1, 1),
        4: (0, -1),
        5: (0, 1),
        6: (1, -1),
        7: (1, 0),
        8: (1, 1),
    }
    step = steps[wise]
    return r + step[0], c + step[1]


def is_valid(r, c):
    return 0 <= r < SIZE and 0 <= c < SIZE


board = [[BLANK] * SIZE for each in range(SIZE)]
board[2][2] = board[3][3] = WHITE
board[2][3] = board[3][2] = BLACK

n = int(input())

for index in range(n):
    r, c = map(int, input().split())
    r, c = r-1, c-1  # to index
    turn = BLACK if index % 2 == 0 else WHITE
    board[r][c] = turn

    for wise in range(1, 9):
        traverses = []
        tr, tc = r, c
        for _ in range(SIZE):
            tr, tc = next_step(tr, tc, wise)
            traverses.append((tr, tc))
            if not is_valid(tr, tc):
                break 
            value = board[tr][tc]
            if value == BLANK:
                break
            if value == turn:
                for traverse in traverses:
                    board[traverse[0]][traverse[1]] = turn
                break

print_board(board)

winner = max(Counter(filter(lambda x: x != BLANK, chain.from_iterable(board))).items(), key=lambda x: x[1])[0]
if winner == WHITE:
    print("White")
else:
    print("Black")
