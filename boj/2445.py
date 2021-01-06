number = int(input())
for each in range(1, number):
    print("*" * each, end="")
    print(" " * (number - each) * 2, end="")
    print("*" * each)
print("*"  * number * 2)
for each in range(number-1, 0, -1):
    print("*" * each, end="")
    print(" " * (number - each) * 2, end="")
    print("*" * each)
