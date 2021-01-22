def is_group_word(s):
    c = list()
    for each in s:
        if each not in c:
            c.append(each)
        if each != c[-1]:
            return False
    return True

n = int(input())

answer = 0
for _ in range(n):
    answer = answer + is_group_word(input())
print(answer)
