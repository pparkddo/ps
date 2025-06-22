import sys;

input = sys.stdin.readline


def solution(n, a, b):
    answer = [0, []]

    for i in range(n):
        if a[i] > b[i]:
            answer[1].append((3, i+1))
            answer[0] += 1
            a[i], b[i] = b[i], a[i]

    for i in range(n):
        for j in range(1, n):
            if a[j-1] > a[j]:
                answer[1].append((1, j))
                answer[0] += 1
                a[j], a[j-1] = a[j-1], a[j]

    for i in range(n):
        for j in range(1, n):
            if b[j-1] > b[j]:
                answer[1].append((2, j))
                answer[0] += 1
                b[j], b[j-1] = b[j-1], b[j]

    return answer


if __name__ == '__main__':
    for _ in range(int(input())):
        n = int(input())
        a = list(map(int, input().split()))
        b = list(map(int, input().split()))
        result = solution(n, a, b)
        print(result[0])
        for each in result[1]:
            print(" ".join(map(str, each)))
