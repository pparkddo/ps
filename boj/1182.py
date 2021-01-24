n, s = map(int, input().split())
a = list(map(int, input().split()))

visit = {}  # {node: ps} 
count = 0

stack = []
stack.extend([(each,) for each in range(n)])

while stack:
    node = stack.pop()
    last_element_index = node[-1]
    ps = visit.get(node[:-1], 0) + a[last_element_index]
    visit.update({node: ps})
    if ps == s:
        count = count + 1
    for index in range(last_element_index+1, n):
        stack.append((*node, index))

print(count)
