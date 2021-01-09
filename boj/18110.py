import sys
import decimal
decimal.getcontext().rounding = decimal.ROUND_HALF_UP


def round_half_up(v):
    return int(decimal.Decimal(v).to_integral_value())


n = int(input())
ex = round_half_up(n * 0.15)

ds = [int(sys.stdin.readline()) for _ in range(n)]
ds.sort()
excluded = ds[ex:len(ds)-ex]

if len(excluded) != 0:
    answer = round_half_up(sum(excluded)/len(excluded))
    print(answer)
else:
    print(0)
