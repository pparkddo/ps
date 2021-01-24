def main():
    a, b, c = map(int, input().split())

    if b == c:
        print(-1)
        return

    break_even_point = (a/(c-b))

    if break_even_point < 0:
        print(-1)
        return
    
    print(int(break_even_point)+1)


main()
