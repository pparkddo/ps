import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Stack;

public class Main {

    private static final Map<Character, Integer> priorities
        = Map.of('+', 1, '-', 1, '*', 2, '/', 2);

    private static String solution(String expression) {
        return convertToPostFix(expression);
    }

    private static String convertToPostFix(String expression) {
        Stack<Character> stack = new Stack<>();
        StringBuilder answer = new StringBuilder();
        expression = "(" + expression + ")";
        for (Character each : expression.toCharArray()) {
            if (each == '(') {
                stack.add(each);
                continue;
            }
            if (each == ')') {
                Character c = null;
                while ((c = stack.pop()) != '(') {
                    answer.append(c);
                }
                continue;
            }
            if (priorities.containsKey(each)) {
                while (stack.peek() != '('
                        && priorities.get(stack.peek()) >= priorities.get(each)) {
                    answer.append(stack.pop());
                }
                stack.add(each);
                continue;
            }
            answer.append(each);
        }
        while (!stack.isEmpty()) {
            answer.append(stack.pop());
        }
        return answer.toString();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String expression = in.readLine();
        in.close();
        System.out.println(solution(expression));
    }
}
