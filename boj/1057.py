import math

n, a, b = map(int, input().split())

for stage in range(1, n):
    a = math.ceil(a/2)
    b = math.ceil(b/2)
    if a == b:
        break

print(stage)
