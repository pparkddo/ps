from collections import Counter

JOB_1 = 2
JOB_2 = 1

n, m, b = map(int, input().split())
heights = []
for _ in range(n):
    heights.extend(list(map(int, input().split())))

counter = Counter(heights)

answers = {}  # {height: time}
for target_height in range(min(counter), max(counter)+1):
    time, used_block = 0, 0
    for each, count in counter.items():
        d = abs(each-target_height)
        if each > target_height:
            time = time + d * count * JOB_1
            used_block = used_block - d * count
        if each < target_height:
            time = time + d * count * JOB_2
            used_block = used_block + d * count
        continue
    if b - used_block >= 0:
        answers.update({target_height: time})

minimum = min(answers.items(), key=lambda x: x[1])[1]
h, t = max(filter(lambda x: x[1] == minimum, answers.items()), key=lambda x: x[0])
print(f"{t} {h}")
