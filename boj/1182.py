from itertools import combinations


n, s = map(int, input().split())
a = list(map(int, input().split()))

count = 0

for length in range(1, len(a)+1):
    for each in combinations(a, length):
        if sum(each) == s:
            count = count + 1

print(count)
