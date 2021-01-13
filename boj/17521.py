n, w = map(int, input().split())
s = [int(input()) for _ in range(n)]

count = 0

for current_price, after_price in zip(s, s[1:]):
    if after_price >= current_price:
        available = w // current_price
        w = w - available * current_price
        count = count + available
    else:
        w = w + count * current_price
        count = 0
    
if count:
    w = w + count * s[-1]

print(w)
