from itertools import combinations 

n, m = [int(each) for each in input().split(" ")]

for each in combinations(range(1, n+1), m):
    print(" ".join([str(e) for e in each]))
