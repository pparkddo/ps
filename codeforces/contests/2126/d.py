import sys; input=sys.stdin.readline; sys.setrecursionlimit(10 ** 6)


def solution(n, k, casinos):
    casinos.sort(key=lambda x: x[0])

    for l, r, real in casinos:
        if l <= k <= r:
            k = max(k, real)

    return k



if __name__ == '__main__':
    for _ in range(int(input())):
        n, k = map(int, input().split())
        casinos = [list(map(int, input().split())) for _ in range(n)]
        print(solution(n, k, casinos))
