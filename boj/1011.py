import sys; input=sys.stdin.readline


def main():
    x, y = map(int, input().split())
    distance = y - x

    n, answer = 0, 0

    while True:
        if distance <= 0:
            break

        n = n + 1

        if distance <= n:
            answer = answer + 1
            break

        distance = distance - (2*n)
        answer = answer + 2
    
    print(answer)


for _ in range(int(input())):
    main()
