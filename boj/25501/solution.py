def recursion(s, left, right):
    if left >= right:
        return 1
    elif s[left] != s[right]:
        return 0
    else:
        return recursion(s, left + 1, right - 1)


def recursion_count(s, left, right):
    if left >= right:
        return 1
    elif s[left] != s[right]:
        return 1
    else:
        return recursion_count(s, left + 1, right - 1) + 1


def main(s):
    return recursion(s, 0, len(s) - 1), recursion_count(s, 0, len(s) - 1)


for _ in range(int(input())):
    answer, count = main(input())
    print(f"{answer} {count}")
