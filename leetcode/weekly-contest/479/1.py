class Solution:
    def sortByReflection(self, nums: List[int]) -> List[int]:
        result = list(sorted([(num, int(str(int("".join(list(reversed(f"{num:b}"))))), 2)) for num in nums], key=lambda x: (x[1], x[0])))
        return list(map(lambda x: x[0], result))