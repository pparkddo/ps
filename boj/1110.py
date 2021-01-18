n = int(input())
answer = n
count = 0

while True:
    count = count + 1
    a, b = n // 10, n % 10
    s = a + b
    s = s if s < 10 else s % 10
    n = b * 10 + s
    if n == answer:
        break

print(count)
