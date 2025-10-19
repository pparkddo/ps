import sys; input=sys.stdin.readline; sys.setrecursionlimit(10**6)


def main(n):
    # def fib(i):
    #     if i <= 1:
    #         return i
    #     return fib(i-1) + fib(i-2)
    dp = [0, 1]
    for i in range(2, n+1):
        dp.append(dp[i-1] + dp[i-2])
    return dp[-1]



if __name__ == '__main__':
    n = int(input())
    print(main(n))
