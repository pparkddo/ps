import sys; input=sys.stdin.readline

s = input().strip()
n = len(s)
m = int(input())
left, right = list(s), list()

for _ in range(m):
    command = input().split()
    operation = command[0]
    if operation == "L":
        if not left:
            continue
        right.append(left.pop())
    elif operation == "D":
        if not right:
            continue
        left.append(right.pop())
    elif operation == "B":
        if not left:
            continue
        left.pop()
    elif operation == "P":
        value = command[1]
        left.append(value)

print("".join([*left, *reversed(right)]))
