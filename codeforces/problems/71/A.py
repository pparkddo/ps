import sys; input=sys.stdin.readline


def solution(s):
    if len(s) <= 10:
        return s

    return s[0] + str(len(s) - 2) + s[-1]


if __name__ == '__main__':
    answer = []

    for _ in range(int(input())):
        answer.append(solution(input().strip()))

    print("\n".join(answer).strip())
