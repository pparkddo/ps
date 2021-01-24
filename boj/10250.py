import math


for _ in range(int(input())):
    h, w, n = map(int, input().split())
    y = n % h if n % h != 0 else h
    x = int(math.ceil(n/h))
    print(f"{y}{x:02}")
