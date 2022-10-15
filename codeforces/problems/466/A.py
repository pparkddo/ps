import sys; input = sys.stdin.readline


def solution(n, m, a, b):
    if m * a <= b:
        return n * a
    else:
        return n // m * b + min((n % m) * a, b)


if __name__ == '__main__':
    print(solution(*map(int, input().split())))
