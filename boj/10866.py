import sys; input=sys.stdin.readline

class Deque:

    def __init__(self):
        from collections import deque
        self._deque = deque()

    def push_front(self, value):
        self._deque.appendleft(value)

    def push_back(self, value):
        self._deque.append(value)

    def pop_front(self):
        if self.empty():
            return -1
        return self._deque.popleft()

    def pop_back(self):
        if self.empty():
            return -1
        return self._deque.pop()
    
    def size(self):
        return len(self._deque)
    
    def empty(self):
        return 1 if self.size() == 0 else 0
    
    def front(self):
        if self.empty():
            return -1
        return self._deque[0]

    def back(self):
        if self.empty():
            return -1
        return self._deque[-1]


deque_ = Deque()

n = int(input())
output = []

for _ in range(n):
    command = input().split()
    operation = command[0]
    if operation == "push_front":
        argument = command[1]
        deque_.push_front(argument)
    elif operation == "push_back":
        argument = command[1]
        deque_.push_back(argument)
    elif operation == "pop_front":
        output.append(deque_.pop_front())
    elif operation == "pop_back":
        output.append(deque_.pop_back())
    elif operation == "size":
        output.append(deque_.size())
    elif operation == "empty":
        output.append(deque_.empty())
    elif operation == "front":
        output.append(deque_.front())
    else:
        output.append(deque_.back())

print("\n".join(map(str, output)))
