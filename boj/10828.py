import sys; input=sys.stdin.readline

n = int(input())

stack = []
output = []

for _ in range(n):
    input_ = input().split()

    command = input_[0]
    if command == "push":
        value = input_[1]
        stack.append(value)
    elif command == "pop":
        if not stack:
            output.append("-1")
            continue
        output.append(stack.pop())
    elif command == "size":
        output.append(str(len(stack)))
    elif command == "empty":
        if stack:
            output.append("0")
        else:
            output.append("1")
    else:
        if not stack:
            output.append("-1")
            continue
        output.append(stack[-1])

print("\n".join(output))
