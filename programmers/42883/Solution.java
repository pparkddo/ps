import java.util.Stack;

class Solution {
    public String solution(String number, int k) {
        Stack<Character> stack = new Stack<>();
        for (char n : number.toCharArray()) {
            while (
                !stack.isEmpty()
               	&& k > 0
                && stack.peek() < n
            ) {
                stack.pop();
                k--;
            }
            stack.push(n);
        }
        StringBuilder answer = new StringBuilder();
        for (char n : stack) {
            answer.append(n);
        }
        return answer.toString();
    }
}
