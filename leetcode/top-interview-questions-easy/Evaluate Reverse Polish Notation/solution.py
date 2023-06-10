class Solution:

    def evalRPN(self, tokens: list[str]) -> int:
        stack: list[int] = []

        for token in tokens:
            if not token.lstrip("-").isnumeric():
                latter, former = stack.pop(), stack.pop()

                result: int

                if token == "+":
                    result = former + latter
                elif token == "-":
                    result = former - latter
                elif token == "*":
                    result = former * latter
                else:  # elif token == "/":
                    result = int(former / latter)

                stack.append(result)
                continue

            stack.append(int(token))

        return stack.pop()
