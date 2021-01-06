number = int(input())
for each in range(1, number+1):
    print(" " * (number-each), end="")
    for index in range(each*2-1):
        if index % 2 == 0:
            print("*", end="")
        else:
            print(" ", end="")
    print()
