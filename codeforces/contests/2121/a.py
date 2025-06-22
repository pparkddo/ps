import sys; input = sys.stdin.readline


def solution(n, s, numbers):
    if len(numbers) == 1:
        return abs(s - numbers[0])

    l, r = numbers[0], numbers[-1]

    return r - l + min(abs(s - l), abs(s - r))


if __name__ == '__main__':
    for _ in range(int(input())):
        n, s = map(int, input().split())
        numbers = list(map(int, input().split()))
        print(solution(n, s, numbers))

