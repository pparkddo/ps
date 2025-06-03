answer = 0

def solution(info, edges):
    global answer
    graph = {}
    for a, b in edges:
        graph.setdefault(a, []).append(b)

    def dfs(n, next_, sheep, wolf):
        global answer
        if info[n] == 0:
            sheep += 1
        else:
            wolf += 1

        if sheep <= wolf:
            return

        answer = max(answer, sheep)

        s = set(next_).union(set(graph.get(n, ())))
        s.remove(n)

        for each in s:
            dfs(each, s, sheep, wolf)

    dfs(0, {0}, 0, 0)

    return answer