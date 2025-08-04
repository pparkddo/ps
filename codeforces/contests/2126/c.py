import sys; input=sys.stdin.readline; sys.setrecursionlimit(10 ** 6)


def solution(n, k, heights) -> str:
    start = heights[k-1]
    heights.sort()
    heights = [h - start for h in heights]

    # print("s", start)
    # print("h", heights)

    current = start
    i = 0
    while i < n:
        if heights[i] > current:
            break
        if heights[i] > 0:
            current = start + heights[i]
        i += 1

    return "YES" if i == n else "NO"


if __name__ == '__main__':
    for _ in range(int(input())):
        n, k = map(int, input().split())
        heights = list(map(int, input().split()))
        print(solution(n, k, heights))

# 5
# 1 1
# 1
# 1 1
# 10
# 4 1
# 1 3 2 4
# 4 1
# 1 3 2 5
# 10 1
# 3 5 5 5 5 5 5 5 5 6
#
