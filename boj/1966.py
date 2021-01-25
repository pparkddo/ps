from collections import deque


t = int(input())

for _ in range(t):
    answer = 0

    n, m = map(int, input().split())
    priorities = list(map(int, input().split()))

    q = deque(priorities)
    
    while True:
        each = q[0]
        if each == max(q):
            answer = answer + 1
            if m == 0:
                print(answer)
                break
            m = m - 1
            q.popleft()
            continue
        if m == 0:
            m = len(q) - 1
        else:
            m = m - 1 
        q.rotate(-1)
