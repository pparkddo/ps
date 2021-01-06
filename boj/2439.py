number = int(input())
for index in range(1, number+1):
    print(f"{' ' * (number-index)}{'*' * index}")
