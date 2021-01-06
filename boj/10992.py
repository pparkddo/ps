number = int(input())
for each in range(1, number+1):
    print(" " * (number-each), end="")
    if each != number:
        for index in range(each*2-1):
            if index == 0 or index == each*2-2:
                print("*", end="")
            else:
                print(" ", end="")
        print()
    else:
        print("*" * (each*2-1))
