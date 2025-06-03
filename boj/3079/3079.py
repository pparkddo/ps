import sys; input = sys.stdin.readline; sys.setrecursionlimit(10 ** 6)


if __name__ == '__main__':
    n, m = map(int, input().split())
    counters = [int(input()) for _ in range(n)]

    min_ = min(counters)
    left, right = 1, m * min_

    def check(v, counters):
        result = 0
        for counter in counters:
            result += v // counter
        return result

    while left + 1 < right:
        mid = (left + right) // 2
        if check(mid, counters) < m:
            left = mid
        else:
            right = mid

    print(right)



