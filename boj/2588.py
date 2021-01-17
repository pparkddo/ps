a = int(input())
b = digit = int(input())

while digit:
    print(a * (digit % 10))
    digit = digit // 10

print(a * b)
