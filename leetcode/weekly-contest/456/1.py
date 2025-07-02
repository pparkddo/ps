class Solution:

    def partitionString(self, s: str) -> List[str]:
        set_ = set()
        answer = []

        seg = ""
        for c in s:
            seg += c
            if seg not in set_:
                set_.add(seg)
                answer.append(seg)
                seg = ""
                continue

        return answer
