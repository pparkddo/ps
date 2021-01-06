from itertools import product

n, m = [int(each) for each in input().split(" ")]

for each in product(range(1, n+1), repeat=m):
    print(" ".join([str(e) for e in each]))
