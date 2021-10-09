import java.util.Stack;

class Solution {

    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == '(') {
                stack.add(c);
            }
            else if (c == ')' && !stack.isEmpty()) {
                stack.pop();
            }
            else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
