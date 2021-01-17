import sys; input=sys.stdin.readline


def round(value, n_digits=0):
    digits = 10**-n_digits
    rounded = (value + int(digits/2)) // digits * digits
    if isinstance(value, int):
        return int(rounded)
    return rounded


n = int(input())
output = []

for _ in range(n):
    value = input().strip()
    rounded = int(value)
    for index in range(len(value)):
        rounded = round(int(rounded), -index)
    output.append(rounded)

print("\n".join(map(str, output)))
