import heapq


class Node:

    def __init__(self, id_, p):
        self.id_ = id_
        self.p = p

    def __repr__(self):
        return f"Node({self.id_}, {self.p})"

    def __lt__(self, other):
        if self.p != other.p:
            return self.p > other.p
        else:
            return self.id_ < other.id_


class EventManager:

    def __init__(self, events: list[list[int]]):
        self.nodes = [Node(each[0], each[1]) for each in events]
        self.d = {each[0]: each[1] for each in events}
        heapq.heapify(self.nodes)
        # print(self.nodes)
        # print(self.d)

    def updatePriority(self, eventId: int, newPriority: int) -> None:
        heapq.heappush(self.nodes, Node(eventId, newPriority))
        self.d[eventId] = newPriority
        # print(self.nodes)

    def pollHighest(self) -> int:
        while self.nodes and (self.nodes[0].id_ not in self.d or self.d[self.nodes[0].id_] != self.nodes[0].p):
            heapq.heappop(self.nodes)
        if not self.nodes:
            return -1
        node = heapq.heappop(self.nodes)
        del self.d[node.id_]
        return node.id_
