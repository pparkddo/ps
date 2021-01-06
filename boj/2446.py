number = int(input())
for each in range(number, 1, -1):
    print(" " * (number - each), end="")
    print("*" * (2 * each - 1), end="\n")
for each in range(1, number+1):
    print(" " * (number - each), end="")
    print("*" * (2 * each - 1), end="\n")
