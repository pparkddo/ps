import sys; input=sys.stdin.readline


n = int(input())
numbers = list(map(int, input().split()))
maximum = max(numbers)
adjusted = [each/maximum*100 for each in numbers]
print(sum(adjusted)/n)
