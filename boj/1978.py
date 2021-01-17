def is_prime_number(number):
    if number <= 1:
        return False
    for i in range(2, number):
        if (number % i) == 0:
            return False
    return True

n = int(input())

count = 0
for each in map(int, input().split()):
    if is_prime_number(each):
        count = count + 1
print(count)
