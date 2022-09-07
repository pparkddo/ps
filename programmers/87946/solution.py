answer = 0


def solution(k, dungeons):
    is_visited = [False] * len(dungeons)
    rec(k, dungeons, 0, is_visited)
    return answer


def rec(k, dungeons, count, is_visited):
    global answer

    if count > answer:
        answer = count

    for i in range(len(dungeons)):
        if is_visited[i]:
            continue

        required, cost = dungeons[i]
        if required > k:
            continue

        is_visited[i] = True
        rec(k - cost, dungeons, count + 1, is_visited)
        is_visited[i] = False


if __name__ == '__main__':
    print(solution(80, [[80, 20], [50, 40], [30, 10]]))
