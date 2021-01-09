import sys; input=sys.stdin.readline

n = int(input())
numbers = {int(each) for each in input().split()}

m = int(input())

output = []
for each in input().split():
    if int(each) in numbers:
        output.append("1")
    else:
        output.append("0")

print("\n".join(output))
