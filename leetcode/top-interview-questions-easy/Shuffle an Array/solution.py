from random import randrange


class Solution:

    def __init__(self, nums: list[int]):
        self.original: list[int] = nums
        self.shuffled = [num for num in nums]

    def reset(self) -> list[int]:
        return self.original

    def shuffle(self) -> list[int]:
        for index in range(len(self.original)):
            random_index = randrange(0, len(self.shuffled))
            self.shuffled[index], self.shuffled[random_index] = self.shuffled[random_index], self.shuffled[index]
        return self.shuffled
