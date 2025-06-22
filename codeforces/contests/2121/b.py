import sys; input = sys.stdin.readline
from collections import Counter


def solution(n, s):
    counter = Counter(s[1:-1])
    r, l = s[0], s[-1]
    if r in counter or l in counter:
        return True
    for v in counter.values():
        if v >= 2:
            return True
    return False

if __name__ == '__main__':
    for _ in range(int(input())):
        n = int(input())
        s = input().strip()
        print("Yes" if solution(n, s) else "No")
