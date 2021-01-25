set = {
    "0": 0,
    "1": 0,
    "2": 0,
    "3": 0,
    "4": 0,
    "5": 0,
    "6": 0,
    "7": 0,
    "8": 0,
}

answer = 0
for each in input():
    each = each if each != "9" else "6"

    if set[each] != 0:
        set[each] = set[each] - 1
        continue

    answer = answer + 1
    for key in set:
        if key == "6":
            set[key] = set[key] + 2
            continue
        set[key] = set[key] + 1
    set[each] = set[each] - 1

print(answer)
