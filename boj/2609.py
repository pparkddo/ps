from math import gcd

x, y = map(int, input().split())
gcd = gcd(x, y)
print(gcd)
print(int(x*y/gcd))
