count = int(input())

for index in range(1, count+1):
    a, b = [int(each) for each in input().split(" ")]
    if a > 0 and b < 10:
        print(f"Case #{index}: {a} + {b} = {a + b}")
