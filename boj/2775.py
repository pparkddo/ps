for _ in range(int(input())):
    k, n = int(input()), int(input())
    c = [list(range(1, n+1))]
    for row in range(1, k+1):
        c.append([sum(c[row-1][:column]) for column in range(1, n+1)])
    print(c[k][n-1])
