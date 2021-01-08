from itertools import combinations

N, M, K = map(int, input().split(" "))


def generate_element(row, column, value):
    return (row, column, value)


def flat(iterable):
    from itertools import chain
    return list(chain.from_iterable(iterable))


def is_adjacent(node):
    for a, b in combinations(node, 2):
        a_row, a_column, _ = a
        b_row, b_column, _ = b
        if (
            (a_row == b_row-1 and a_column == b_column)
            or (a_row == b_row+1 and a_column == b_column)
            or (a_row == b_row and a_column == b_column-1)
            or (a_row == b_row and a_column == b_column+1)
        ):
            return True
    return False


def get_flatted_index(element):
    row, column, _  = element
    return row * N + column


matrix = []
for row in range(N):
    elements = [
        generate_element(row, column, int(each)) 
        for column, each in enumerate(input().split())
    ]
    matrix.append(elements)

# node = {element, element, ...}
# element = (row, column, value)
answer = -99999999
stack = list()

flatted = flat(matrix)
root_nodes = [(each,) for each in flatted]
stack.extend(root_nodes)

while stack:
    node = stack.pop()
    if is_adjacent(node):
        continue
    if len(node) == K:
        answer = max(answer, sum([value for *_, value in node]))
        continue
    for index in range(get_flatted_index(node[-1])+1, len(flatted)):
        stack.append((*node, flatted[index]))

print(answer)
