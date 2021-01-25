import sys; input=sys.stdin.readline

stack = []

for _ in range(int(input())):
    number = int(input().strip())
    if number == 0:
        stack.pop()
        continue
    stack.append(number)

print(sum(stack))
