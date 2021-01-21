from collections import Counter


counter = Counter(input().lower())
items = sorted(counter.items(), key=lambda x: x[1], reverse=True)
if len(items) == 1:
    print(items[0][0].upper())
else:
    first, second = items[0], items[1]
    if first[1] == second[1]:
        print("?")
    else:
        print(first[0].upper())
