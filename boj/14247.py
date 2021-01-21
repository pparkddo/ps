import sys; input=sys.stdin.readline

n = int(input())
h = list(map(int, input().split()))
a = list(map(int, input().split()))

answer = 0

a.sort()
for index, each in enumerate(a):
    answer = answer + h[index] + each * index

print(answer)
