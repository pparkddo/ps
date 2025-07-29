import sys; input = sys.stdin.readline


if __name__ == '__main__':
    n = int(input())
    numbers = list(map(int, input().split()))

    is_even = numbers[0] % 2 == 0
    c = []
    for i in range(1, n):
        if is_even != (numbers[i] % 2 == 0):
            c.append(i-1)
            c.append(i)
            break

    # print(c)
    next_i = (c[-1] + 1) % n
    if numbers[next_i] % 2 == numbers[c[-1]] % 2:
        print(c[0] + 1)
    else:
        print(c[-1] + 1)
