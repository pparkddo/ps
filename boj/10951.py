while True:
    try:
        a, b = [int(each) for each in input().split(" ")]
        if a > 0 and b < 10:
            print(a + b)
    except Exception:
        break
