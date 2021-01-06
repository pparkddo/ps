from itertools import combinations_with_replacement

n, m = [int(each) for each in input().split(" ")]

for each in combinations_with_replacement(range(1, n+1), r=m):
    print(" ".join([str(e) for e in each]))

# row = list()

# def dfs(depth, index, n, m):
#     if depth == m:
#         print(" ".join([str(e) for e in row]))
#         return
#     for i in range(index, n):
#         row.append(i+1)
#         dfs(depth+1, i, n, m)
#         row.pop()

# dfs(0, 0, n, m)
