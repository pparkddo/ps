N = int(input())
TP = []

for index in range(N):
    values = [int(each) for each in input().split()]
    TP.append((*values, index))

# element = (time, point, sequence)
stack = list([(each,) for each in TP])
candidates = {("",): 0}  # {node: acc_point}  # default value: 0 !

while stack:
    node = stack.pop()
    time, point, sequence = node[-1]
    if sequence + time > N:
        continue
    previous_node = node[:-1]
    previous_acc_point = candidates.get(previous_node, 0)
    candidates.update({node: previous_acc_point+point})
    for index in range(sequence+time, N):
        stack.append((*node, TP[index]))

print(max(candidates.items(), key=lambda x: x[1])[1])
