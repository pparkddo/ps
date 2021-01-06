count = input()
numbers = [int(each) for each in input()]

answer = 0
for number in numbers:
    answer = answer + number
print(answer)
