import sys; input=sys.stdin.readline

n = int(input())
numbers = {int(each) for each in input().split()}

m = int(input())
for each in input().split():
    if int(each) in numbers:
        print(1)
    else:
        print(0)
