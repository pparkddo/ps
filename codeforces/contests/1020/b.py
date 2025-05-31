import sys; input = sys.stdin.readline


def solution(n, x):
    answer = []

    c = 0
    for _ in range(x):
        answer.append(str(c))
        c += 1

    c = 0
    for _ in range(n - x):
        answer.append(str(n - 1 - c))
        c += 1

    return " ".join(answer)


if __name__ == '__main__':
    answer = []
    for _ in range(int(input().strip())):
        answer.append(solution(*map(int, input().split())))
    print("\n".join(answer))
