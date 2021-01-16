from itertools import combinations 

n, m = [int(each) for each in input().split(" ")]

for each in combinations(range(1, n+1), m):
    print(" ".join([str(e) for e in each]))

# using dfs
# n, m = [int(each) for each in input().split(" ")]

# stack = []
# stack.extend([(each,) for each in range(n, 0, -1)])

# while stack:
#     node = stack.pop()
#     if len(node) == m:
#         print(" ".join(map(str, node)))
#         continue
#     for each in range(n, node[-1], -1):
#         stack.append((*node, each))
