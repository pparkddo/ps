import sys; input=sys.stdin.readline
from itertools import combinations


def get_team_score(s, team):
    score = 0
    for i, j in combinations(team, 2):
        score = score + s[i][j] + s[j][i]
    return score


n = int(input())
s = []
for _ in range(n):
    s.append(list(map(int, input().split())))

possibles = list(combinations(range(n), n//2))
length = len(possibles)

minimum = -1
for index in range(length):
    if index == (length//2): 
        break
    a, b = possibles[index], possibles[length-1-index]
    start, link = get_team_score(s, a), get_team_score(s, b)
    diff = abs(start-link)
    if minimum == -1 or diff < minimum:
        minimum = diff
    if minimum == 0:
        break

print(minimum)
