from sys import stdin
from string import ascii_uppercase
from itertools import combinations, chain


MOVE = "-"
EMPTY = "?"
EMPTY_ROW = 0


K = int(input())
N = int(input())
expected = input()

participants = ascii_uppercase[:K]
ladder = []
for index in range(N):
    row = list(stdin.readline())
    if row[0] == EMPTY:
        EMPTY_ROW = index
    ladder.append(row)


def simulate(partial, participants):
    result = [""] * len(participants)
    for sequence in range(len(participants)):
        participant = participants[sequence]
        for row in range(len(partial)):
            if sequence > 0 and partial[row][sequence-1] == MOVE:
                sequence = sequence - 1
                continue
            if sequence < len(participants)-1 and partial[row][sequence] == MOVE:
                sequence = sequence + 1
                continue
        result[sequence] = participant
    return result


up_ladder = ladder[:EMPTY_ROW]
down_ladder = ladder[EMPTY_ROW+1:]
reversed_down_ladder = list(reversed(down_ladder))

up_result = simulate(up_ladder, participants)
down_result = simulate(reversed_down_ladder, expected)

answer = []

for index in range(len(participants)-1):
    if up_result[index] == down_result[index]:
        answer.append("*")
    elif up_result[index] == down_result[index+1] or up_result[index-1] == down_result[index]:
        up_result[index], up_result[index+1] = up_result[index+1], up_result[index]
        answer.append("-")
    else:
        answer = ["x"] * (K-1)
        break

print("".join(answer))
