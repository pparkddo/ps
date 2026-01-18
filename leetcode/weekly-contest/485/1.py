class Solution:
    def vowelConsonantScore(self, s: str) -> int:
        vowels = set(["a", "e", "i", "o", "u"])

        v, c = 0, 0
        for cc in s:
            if cc in vowels:
                v += 1
            elif cc.isalpha():
                c += 1

        if c == 0:
            return 0
        else:
            return v // c
