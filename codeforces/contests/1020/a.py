import sys; input = sys.stdin.readline

f = lambda n, k: n*k + n - (2 * k)

def solution(s):
    k = 0
    for each in s:
        if each == "1":
            k+=1

    return f(len(s), k)

if __name__ == '__main__':
    for _ in range(int(input().strip())):
        input()
        print(solution(input().strip()))
