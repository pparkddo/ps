import sys; input=sys.stdin.readline


def solution(w):
    return w % 2 == 0 and w != 2


if __name__ == '__main__':
    w = int(input())
    print("YES" if solution(w) else "NO")
