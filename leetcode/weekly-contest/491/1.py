class Solution:
    def trimTrailingVowels(self, s: str) -> str:
        vowels = set(["a", "e", "i", "o", "u"])
        answer = ""

        found = False
        for c in s[::-1]:
            if c in vowels and not found:
                continue
            found = True
            answer += c
        
        return answer[::-1]
