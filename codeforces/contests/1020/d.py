import sys; input = sys.stdin.readline
import math


def solution(n, m, a, b):
    prefix = [-1 for _ in range(m)]
    suffix = [-1 for _ in range(m)]

    j = n - 1
    for i in range(m - 1, -1, -1):
        while j >= 0 and a[j] < b[i]:
            j -= 1

        suffix[i] = j
        j -= 1

    j = 0
    for i in range(m):
        while j < n and a[j] < b[i]:
            j += 1

        prefix[i] = j
        j += 1

    if prefix[-1] < n:
        return 0

    answer = math.inf
    for i in range(m):
        previous = -1 if i == 0 else prefix[i - 1]
        next_ = n if i == m - 1 else suffix[i + 1]
        if previous < next_:
            answer = min(answer, b[i])

    return answer if answer != math.inf else -1


if __name__ == '__main__':
    answer = []
    for _ in range(int(input().strip())):
        n, m = map(int, input().split())
        a = list(map(int, input().split()))
        b = list(map(int, input().split()))
        answer.append(solution(n, m, a, b))
    print("\n".join(map(str, answer)))
