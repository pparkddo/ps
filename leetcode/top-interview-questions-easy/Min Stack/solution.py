from dataclasses import dataclass


class MinStack:

    def __init__(self):
        self.stack: list[MinStackItem] = []

    def push(self, val: int) -> None:
        min_value: int = min(val, self.stack[-1].min_value) if self.stack else val
        self.stack.append(MinStackItem(val, min_value))

    def pop(self) -> None:
        self.stack.pop()

    def top(self) -> int:
        return self.stack[-1].value

    def getMin(self) -> int:
        return self.stack[-1].min_value


@dataclass
class MinStackItem:

    value: int
    min_value: int


# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(val)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()
