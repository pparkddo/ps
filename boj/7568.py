n = int(input())
a = [tuple(map(int, input().split())) for each in range(n)]

answer = []
for each in a:
    k = 1
    x, y = each
    for e in a:
        p, q = e
        if p > x and q > y:
            k = k + 1
    answer.append(str(k))

print(" ".join(answer))
