from dataclasses import dataclass
from uuid import uuid4


@dataclass
class Node:

    id_: str
    value: int
    depth: int

    def __init__(self, value, depth):
        self.id_ = str(uuid4())
        self.value = value
        self.depth = depth


def solution(numbers, target):
    visit = []
    stack = []

    root_node = Node(0, 0)
    stack.append(root_node)

    while stack:
        node = stack.pop()
        if node not in visit:
            visit.append(node)
            if node.depth < len(numbers):
                stack.append(Node(node.value + numbers[node.depth], node.depth+1))
                stack.append(Node(node.value - numbers[node.depth], node.depth+1))
    
    filtered = list(filter(lambda x: x.depth == len(numbers) and x.value == target, visit))
    return len(filtered)


def generate_id():
    return str(uuid4())


def generate_node(value, depth):
    # node = {id, value, depth}
    return {
        "id": generate_id(),
        "value": value,
        "depth": depth
    }


def solution(numbers, target):
    visit = {}  # {id: (value, depth), ...}
    stack = []

    root_node = generate_node(0, 0)
    stack.append(root_node)

    while stack:
        node = stack.pop()
        if node["id"] not in visit:
            current_value = node["value"]
            current_depth = node["depth"]
            visit.update({node["id"]: (current_value, current_depth)})
            if current_depth < len(numbers):
                new_number = numbers[current_depth]
                stack.append(generate_node(current_value + new_number, current_depth+1))
                stack.append(generate_node(current_value - new_number, current_depth+1))
    
    filtered = list(filter(lambda x: x[1] == len(numbers) and x[0] == target, visit.values()))
    return len(filtered)


if __name__ == "__main__":
    questions = [
        ([1, 1, 1, 1, 1], 3),
    ]
    answers = [
        5
    ]

    for q, a in zip(questions, answers):
        answer = solution(*q)
        if a == answer:
            print("[o] good")
        else:
            print("[x] bad", answer)
