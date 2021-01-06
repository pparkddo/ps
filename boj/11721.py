words = input()

for index in range(0, len(words), 10):
    print(words[index:index+10])
