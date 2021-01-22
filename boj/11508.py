import sys; input=sys.stdin.readline


n = int(input())
costs = sorted([int(input()) for _ in range(n)], reverse=True)

answer = 0

for index in range(0, len(costs), 3):
    answer = answer + sum(costs[index:index+2])
    
print(answer)
