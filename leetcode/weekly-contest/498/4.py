from functools import cache


class Solution:
    def countGoodIntegersOnPath(self, l: int, r: int, directions: str) -> int:
        p, i = {0}, 0
        for d in directions:
            i += 4 if d == 'D' else 1
            p.add(i)

        def f(s):
            s = str(s).zfill(16)
            @cache
            def h(i, v, t):
                if i == 16: return 1
                res, lim = 0, int(s[i]) if t else 9
                for x in range(lim + 1):
                    if i in p:
                        if x >= v:
                            res += h(i + 1, x, t and x == lim)
                    else:
                        res += h(i + 1, v, t and x == lim)
                return res
            return h(0, 0, True)

        return f(r) - f(l - 1)
