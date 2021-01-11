def main(n):
    divisors = []
    divisor_sum = 0

    for each in range(1, n):
        if n % each == 0:
            divisors.append(str(each))
            divisor_sum = divisor_sum + each

    if n == divisor_sum:
        print(f"{n} = {' + '.join(divisors)}")
    else:
        print(f"{n} is NOT perfect.")

while True:
    n = int(input())
    if n == -1:
        break
    main(n)
