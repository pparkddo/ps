count = int(input())

for _ in range(count):
    a, b = [int(each) for each in input().split(",")]
    if a > 0 and b < 10:
        print(a + b)
