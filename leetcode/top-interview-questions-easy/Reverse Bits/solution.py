class Solution:

    def reverseBits(self, n: int) -> int:
        result: int = 0
        for _ in range(32):
            result <<= 1
            if n & 1 == 1:
                result += 1
            n >>= 1
        return result


if __name__ == '__main__':
    print(Solution().reverseBits(43261596))
