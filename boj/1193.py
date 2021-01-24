def main(x):
    start = end = count = 1

    while True:
        if start <= x <= end:
            break
        count = count + 1
        start = end + 1
        end = start + count - 1

    value = start if count % 2 == 0 else end
    numerator, denominator = 1, count

    order = 1 if count % 2 == 0 else -1
    while True:
        if x == value:
            break
        numerator = numerator + 1
        denominator = denominator - 1
        value = value + order

    print(f"{numerator}/{denominator}")


main(int(input()))
