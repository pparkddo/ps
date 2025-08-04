import sys; input=sys.stdin.readline; sys.setrecursionlimit(10 ** 6)


def gcd(a, b):
    while b:
        r = a % b
        a = b
        b = r
    return a


def lcm(a, b):
    return a * b // gcd(a, b)


def solution(n, p, s):
    for i in range(1, n):
        if p[i-1] < p[i] or p[i-1] % p[i] != 0:
            return "NO"

    for i in range(n-2, -1, -1):
        if s[i+1] < s[i] or s[i+1] % s[i] != 0:
            return "NO"

    result = [lcm(p[i], s[i]) for i in range(n)]
    # print(result)

    if result[0] != p[0]:
        return "NO"

    if result[-1] != s[-1]:
        return "NO"

    for i in range(1, n):
        if gcd(p[i-1], result[i]) != p[i]:
            return "NO"

    for i in range(n-2, -1, -1):
        if gcd(s[i+1], result[i]) != s[i]:
            return "NO"

    return "YES"


if __name__ == '__main__':
    for _ in range(int(input())):
        n = int(input())
        p = list(map(int, input().split()))
        s = list(map(int, input().split()))
        print(solution(n, p, s))
