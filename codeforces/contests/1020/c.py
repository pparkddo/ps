import sys; input = sys.stdin.readline
import math


def solution(n, k, a, b):
    target = None

    min_ = math.inf
    max_ = -math.inf

    for i, j in zip(a, b):
        if j == -1:
            min_ = min(min_, i)
            max_ = max(max_, i)
        else:
            if target is None:
                target = i + j
            else:
                if target != i + j:
                    return 0

    if target is None:
        t = max_ - min_
        return k - t + 1

    if target < max_:
        return 0

    if target - min_ > k:
        return 0

    return 1


if __name__ == '__main__':
    answer = []
    for _ in range(int(input().strip())):
        n, k = map(int, input().split())
        a = list(map(int, input().split()))
        b = list(map(int, input().split()))
        answer.append(solution(n, k, a, b))
    print("\n".join(map(str, answer)))
