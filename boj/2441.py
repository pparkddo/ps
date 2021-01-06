number = int(input())
for index in range(number, 0, -1):
    print(f"{' ' * (number-index)}{'*' * index}")
