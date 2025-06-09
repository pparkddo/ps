import sys; input = sys.stdin.readline; sys.setrecursionlimit(10 ** 6)
import math

if __name__ == '__main__':
    n = int(input())
    solutions = list(map(int, input().split()))

    min_ = math.inf
    answer = None
    left, right = 0, n - 1
    while left < right:
        s = solutions[left] + solutions[right]
        if abs(s) < min_:
            answer = (solutions[left], solutions[right])
            min_ = abs(s)

        if s < 0:
            left += 1
        else:
            right -= 1

    print(" ".join(map(str, answer)))
