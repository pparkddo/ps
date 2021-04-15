import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

class Solution {

    private boolean isCorrect(Deque<Character> deque) {
        Stack<Character> stack = new Stack<>();

        for (Character each : deque) {
            if (each == '(' || each == '{' || each == '[') {
                stack.push(each);
                continue;
            }

            if (stack.isEmpty()) {
                return false;
            }

            if (each == ')') {
                if (stack.pop() != '(') {
                    return false;
                }
            }
            if (each == '}') {
                if (stack.pop() != '{') {
                    return false;
                }
            }
            if (each == ']') {
                if (stack.pop() != '[') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private void rotate(Deque<Character> deque) {
        deque.addLast(deque.pop());
    }

    private Deque<Character> getDeque(String s) {
        Deque<Character> deque = new LinkedList<>();
        for (char each : s.toCharArray()) {
            deque.add(each);
        }
        return deque;
    }

    public int solution(String s) {
        Deque<Character> deque = getDeque(s);
        int answer = 0;
        for (int index = 0; index < s.length(); index++) {
            rotate(deque);
            if (isCorrect(deque)) {
                answer++;
            }
        }
        return answer;
    }
}