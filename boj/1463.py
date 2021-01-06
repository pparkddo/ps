import string
import random
from collections import deque

TARGET_NUMBER = 1
RANDOM_STRING_ARRAY = [*string.ascii_uppercase, *string.digits]


def generate_node(value, depth):
    return {
        "id": ''.join(random.choices(RANDOM_STRING_ARRAY, k=8)),
        "value": value,
        "depth": depth
    }


number = int(input())

visit = dict()  # {value: depth}
queue = deque()

root_node = generate_node(number, 0)
queue.append(root_node)

while queue:
    node = queue.popleft()
    value, depth = node["value"], node["depth"]
    if value not in visit:
        visit.update({value: depth})
        if value == TARGET_NUMBER:
            break
        if value % 3 == 0:
            queue.append(generate_node(value/3, depth+1))
        if value % 2 == 0:
            queue.append(generate_node(value/2, depth+1))
        queue.append(generate_node(value-1, depth+1))

print(visit[TARGET_NUMBER])
