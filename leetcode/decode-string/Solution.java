import java.util.Stack;
import java.util.stream.Collectors;

class Solution {

    private String getInnerCharacters(Stack<Character> stack) {
        StringBuilder repeated = new StringBuilder();
        while (true) {
            char c = stack.pop();
            if (c == '[') {
                break;
            }
            repeated.append(c);
        }
        return repeated.reverse().toString();
    }

    private int getMultiplier(Stack<Character> stack) {
        StringBuilder multiplier = new StringBuilder();
        while (!stack.isEmpty()) {
            char next = stack.peek();
            if (next < '0' || next > '9') {
                break;
            }
            multiplier.append(stack.pop() - '0');
        }
        return Integer.parseInt(multiplier.reverse().toString());
    }

    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (char each : s.toCharArray()) {
            if (each == ']') {
                String inner = getInnerCharacters(stack);
                int multiplier = getMultiplier(stack);
                for (int r = 0; r < multiplier; r++) {
                    for (char i : inner.toCharArray()) {
                        stack.push(i);
                    }
                }
                continue;
            }
            stack.push(each);
        }
        return stack.stream().map(each -> String.valueOf(each)).collect(Collectors.joining());
    }
}