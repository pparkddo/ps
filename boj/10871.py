n, x = map(int, input().split())
print(" ".join(map(str, filter(lambda each: each < x, list(map(int, input().split()))))))
