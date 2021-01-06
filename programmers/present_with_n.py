import random
import string

RANDOM_STRING_ARRAY = [*string.ascii_uppercase, *string.digits]
MAXIMUM_DEPTH = 8


def generate_node(value, depth):
    return {
        "id": ''.join(random.choices(RANDOM_STRING_ARRAY, k=8)),
        "value": value,
        "depth": depth
    }


def get_lower_depth(current_depth, previous_depth):
    return current_depth if current_depth < previous_depth else previous_depth
    

def solution(N, number):
    visit = dict()
    stack = list()
    
    root_node = generate_node(0, 0)
    stack.append(root_node)
    
    while stack:
        node = stack.pop()
        id_, value, depth = node["id"], node["value"], node["depth"]
        if id_ not in visit:
            lower_depth = get_lower_depth(depth, visit.get(value, MAXIMUM_DEPTH))
            visit.update({value: lower_depth})
            for count in range(1, MAXIMUM_DEPTH-depth+1):
                n = int(f"{N}" * count)
                stack.append(generate_node(value + n, depth + count))
                stack.append(generate_node(value - n, depth + count))
                stack.append(generate_node(value * n, depth + count))
                stack.append(generate_node(value // n, depth + count))
                
    return visit.get(number, -1)


if __name__ == "__main__":
    questions = [
        (5, 12),
        (2, 11),
    ]
    answers = [
        4,
        3,
    ]

    for q, a in zip(questions, answers):
        answer = solution(*q)
        if a == answer:
            print("[o] good")
        else:
            print("[x] bad", answer)