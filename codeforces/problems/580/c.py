import sys; input=sys.stdin.readline; sys.setrecursionlimit(10 ** 6)


def main(n, m, cats, graph):
    visited = set()
    answer = 0

    # print(graph)
    # print(cats)

    def dfs(node, count):
        nonlocal answer
        # print(node, count)
        if count > m:
            return

        visited.add(node)

        leaf = True
        for next_node in graph.get(node, []):
            if next_node in visited:
                continue
            leaf = False
            next_count = 0 if cats[next_node-1] == 0 else count + 1
            dfs(next_node, next_count)
        if leaf:
            answer += 1

    count = 0 if cats[1-1] == 0 else 1
    dfs(1, count)
    return answer



if __name__ == '__main__':
    n, m = map(int, input().split())
    cats = list(map(int, input().split()))

    graph = {}
    for _ in range(n-1):
        a, b = map(int, input().split())
        graph.setdefault(a, []).append(b)
        graph.setdefault(b, []).append(a)

    print(main(n, m, cats, graph))
