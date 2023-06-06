import random


class RandomizedSet:

    def __init__(self):
        self.dict_: dict[int, int] = dict()
        self.list_: list[int] = list()

    def insert(self, val: int) -> bool:
        if val in self.dict_:
            return False

        self.list_.append(val)
        self.dict_[val] = len(self.list_) - 1
        return True

    def remove(self, val: int) -> bool:
        if val not in self.dict_:
            return False

        index_to_be_replaced: int = self.dict_[val]
        current_last_element: int = self.list_[-1]

        self.dict_[current_last_element] = index_to_be_replaced
        self.list_[index_to_be_replaced] = current_last_element

        del self.dict_[val]
        self.list_.pop()

        return True

    def getRandom(self) -> int:
        # return random.choice(self.list_)
        index: int = random.randint(0, len(self.list_)-1)
        return self.list_[index]

# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()
