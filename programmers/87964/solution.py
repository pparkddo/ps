def solution(rectangle, characterX, characterY, itemX, itemY):
    rectangle = [(x1*2, y1*2, x2*2, y2*2) for x1, y1, x2, y2 in rectangle]
    characterX = characterX * 2
    characterY = characterY * 2
    itemX = itemX * 2
    itemY = itemY * 2

    graph = {}

    for x1, y1, x2, y2 in rectangle:
        for x in range(x1 + 1, x2 + 1):
            graph.setdefault((x-1, y1), []).append((x, y1))
            graph.setdefault((x, y1), []).append((x-1, y1))
            graph.setdefault((x - 1, y2), []).append((x, y2))
            graph.setdefault((x, y2), []).append((x - 1, y2))
        for y in range(y1 + 1, y2 + 1):
            graph.setdefault((x1, y-1), []).append((x1, y))
            graph.setdefault((x1, y), []).append((x1, y-1))
            graph.setdefault((x2, y-1), []).append((x2, y))
            graph.setdefault((x2, y), []).append((x2, y-1))

    for each in graph:
        print(each, ":", graph[each])

    def is_in_rectangle(point):
        for x1, y1, x2, y2 in rectangle:
            if x1 < point[0] < x2 and y1 < point[1] < y2:
                return True
        return False

    def is_in_line(point1, point2):
        x1, y1 = point1
        x2, y2 = point2
        return x1 == x2 or y1 == y2

    path = []
    visited = set()
    def dfs(point, previous_point):
        visited.add(point)
        path.append(point)
        is_intersection = len(graph[point]) > 2
        next_points = graph.get(point, [])
        print(point, previous_point, is_intersection, next_points)
        for next_point in next_points:
            if next_point in visited:
                continue
            if is_in_rectangle(next_point):
                continue
            if is_intersection and is_in_line(next_point, previous_point):
                continue
            dfs(next_point, point)
    dfs((characterX, characterY), (-1, -1))
    print(path)

    result = 0
    for x, y in path:
        if x == itemX and y == itemY:
            break
        result += 1

    print(result, len(path)-result, len(path))

    return min(len(path)-result, result) // 2
