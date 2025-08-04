import sys; input=sys.stdin.readline; sys.setrecursionlimit(10 ** 6)

if __name__ == '__main__':
    for _ in range(int(input())):
        n = input()
        s = set(n)

        for i in range(10):
            if str(i) in s:
                print(i)
                break
