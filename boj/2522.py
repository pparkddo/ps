number = int(input())
for each in range(1, number):
    print(" " * (number - each), end="")
    print("*" * each)
print("*"  * number)
for each in range(number-1, 0, -1):
    print(" " * (number - each), end="")
    print("*" * each)
