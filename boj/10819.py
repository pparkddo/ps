n = int(input())
a = list(map(int, input().split()))

stack = []
stack.extend([(each,) for each in range(len(a))])
diff = {}  # {node: value}

while stack:
    node = stack.pop()
    if len(node) > 1:
        value = abs(a[node[-2]]-a[node[-1]])
        diff.update({node: diff.get(node[:-1], 0) + value})
    for index in range(len(a)):
        if index in node:
            continue
        stack.append((*node, index))

maximum = None
for node, value in diff.items():
    if len(node) != n:
        continue
    if maximum is None:
        maximum = value
    if value > maximum:
        maximum = value

print(maximum)
