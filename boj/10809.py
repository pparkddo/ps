from string import ascii_lowercase


s = input()

output = []
for each in ascii_lowercase:
    if each not in s:
        output.append(-1)
        continue
    output.append(s.index(each))
print(" ".join(map(str, output)))
