import sys; input=sys.stdin.readline

class Queue:

    def __init__(self):
        from collections import deque
        self._queue = deque()

    def push(self, value):
        self._queue.append(value)

    def pop(self):
        if self.empty():
            return -1
        return self._queue.popleft()
    
    def size(self):
        return len(self._queue)
    
    def empty(self):
        return 1 if self.size() == 0 else 0
    
    def front(self):
        if self.empty():
            return -1
        return self._queue[0]

    def back(self):
        if self.empty():
            return -1
        return self._queue[-1]


queue = Queue()

n = int(input())
output = []

for _ in range(n):
    command = input().split()
    operation = command[0]
    if operation == "push":
        argument = command[1]
        queue.push(argument)
    elif operation == "pop":
        output.append(queue.pop())
    elif operation == "size":
        output.append(queue.size())
    elif operation == "empty":
        output.append(queue.empty())
    elif operation == "front":
        output.append(queue.front())
    else:
        output.append(queue.back())

print("\n".join(map(str, output)))
