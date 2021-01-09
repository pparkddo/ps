n = int(input())
numbers = [int(input()) for _ in range(n)]
numbers.sort()
for number in numbers:
    print(number)
