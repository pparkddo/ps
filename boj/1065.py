def get_digits(n):
    digits = []
    while n:
        digits.append(n % 10)
        n = n // 10
    return digits


def get_diffs(digits):
    diffs = []
    for previous, next_ in zip(digits, digits[1:]):
        diffs.append(next_-previous)
    return diffs


def is_han(n):
    digits = get_digits(n)
    diffs = get_diffs(digits)
    return len(set(diffs)) <= 1


count = 0
for n in range(1, int(input())+1):
    count = count + int(is_han(n))
print(count)
