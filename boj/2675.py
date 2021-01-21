t = int(input())

for _ in range(t):
    r, s = input().split()
    print("".join([each * int(r) for each in s]))
