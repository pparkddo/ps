import sys; input=sys.stdin.readline; sys.setrecursionlimit(10 ** 6)


def solution(n, k, a) -> int:
    i = 0
    answer = 0

    while i < n:
        s = 0
        while s < k and i < n and a[i] == 0:
            i += 1
            s += 1
        if s == k:
            answer += 1
        i += 1

    return answer


if __name__ == '__main__':
    for _ in range(int(input())):
        n, k = map(int, input().split())
        a = list(map(int, input().split()))
        print(solution(n, k, a))