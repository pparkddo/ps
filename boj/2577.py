from collections import Counter
from functools import reduce


number = reduce(lambda x, y: x*y, [int(input()) for _ in range(3)])
counter = Counter(str(number))
for each in range(10):
    print(counter.get(str(each), 0))
