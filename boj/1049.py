import sys; input=sys.stdin.readline

n, m = map(int, input().split())

pps, ps = [], []
mpp, mp = 1001, 1001
for _ in range(m):
    pp, p = map(int, input().split())
    if pp < mpp:
        mpp = pp
    if p < mp:
        mp = p

if mpp >= mp * 6:
    mpp = mp * 6

pc = n // 6
c = n % 6

print(min(pc * mpp + c * mp, (pc+1) * mpp))
