def get_digits(n):
    digits = []
    while n:
        digits.append(n % 10)
        n = n // 10
    return digits


def d(n):
    return n + sum(get_digits(n))


self_numbers = set(range(1, 10001)) - {d(n) for n in range(1, 10001)}

print("\n".join(map(str, sorted(list(self_numbers)))))
