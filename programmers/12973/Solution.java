import java.util.Stack;

class Solution {

    public int solution(String s) {
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        for (int index = s.length()-1; index >= 0; index--) {
            right.add(s.charAt(index));
        }

        for (int index = 0; index < s.length(); index++) {
            if (!left.isEmpty() && !right.isEmpty() && left.peek() == right.peek()) {
                left.pop();
                right.pop();
                continue;
            }
            left.add(right.pop());
        }

        return left.isEmpty() ? 1 : 0;
    }
}
