def is_pattern_matched(s, patterns):
    if not s:
        return False
    for pattern in patterns:
        if s in pattern:
            return True
    return False


patterns = [
    "c=",
    "c-",
    "dz=",
    "d-",
    "lj",
    "nj",
    "s=",
    "z=",
]
maximum_pattern_length = max(map(len, patterns))

count, buffer = 0, []
for each in input():
    if "".join([*buffer, each]) in patterns:
        buffer.clear()
        count = count + 1
        continue
    if is_pattern_matched("".join([*buffer, each]), patterns):
        buffer.append(each)
        continue
    if is_pattern_matched(each, patterns):
        count = count + len(buffer)
        buffer.clear()
        buffer.append(each)
        continue
    if len(buffer) == maximum_pattern_length:
        count = count + len(buffer)
        buffer.clear()
        continue
    count = count + 1 + len(buffer)
    buffer.clear()

print(count + len(buffer))
    