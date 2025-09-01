class Solution:
    def recoverOrder(self, order: List[int], friends: List[int]) -> List[int]:
        s = set(friends)
        return list(filter(lambda x: x in s ,order))