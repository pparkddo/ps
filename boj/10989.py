import sys; input=sys.stdin.readline
MAX_VALUE = 10001

n = int(input())
c = [0] * MAX_VALUE

for _ in range(n):
    number = int(input())
    c[number] = c[number] + 1

for number in range(MAX_VALUE):
    for _ in range(c[number]):
        print(number)
