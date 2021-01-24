def main():
    n = int(input())

    count = s = 1

    while n > s:
        s = s + count * 6
        count = count + 1

    print(count)


main()
