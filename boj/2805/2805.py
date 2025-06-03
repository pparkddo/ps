import sys; input = sys.stdin.readline; sys.setrecursionlimit(10 ** 6)


if __name__ == '__main__':
    n, m = map(int, input().split())
    trees = list(map(int, input().split()))

    left, right = 0, max(trees)

    def check(v, trees):
        result = 0
        for tree in trees:
            result += max(tree - v, 0)
        return result

    while left + 1 < right:
        mid = (left + right) // 2

        if check(mid, trees) >= m:
            left = mid
        else:
            right = mid

    print(left)

