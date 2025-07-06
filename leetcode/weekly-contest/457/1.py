import re


class Solution:

    def validateCoupons(self, code: List[str], businessLine: List[str], isActive: List[bool]) -> List[str]:
        n = len(code)
        business = {"electronics": 1, "grocery": 2, "pharmacy": 3, "restaurant": 4}
        p = re.compile(r'^[a-zA-Z0-9_]+$')

        result = []
        for i in range(n):
            c, b, a = code[i], businessLine[i], isActive[i]
            if p.match(c) and b in business and a:
                result.append((c, business[b]))

        return list(map(lambda x: x[0], sorted(result, key=lambda x: (x[1], x[0]))))
