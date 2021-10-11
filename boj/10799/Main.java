import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    private static int solution(String expression) {
        int answer = 0;

        Stack<Character> stack = new Stack<>();
        for (int index = 0; index < expression.length(); index++) {
            char c = expression.charAt(index);
            if (c == ')') {
                stack.pop();
                char previous = expression.charAt(index-1);
                if (previous == '(') {
                    answer += stack.size();
                } else {
                    answer++;
                }
            }
            else if (c == '(') {
                stack.push(c);
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String expression = in.readLine();
        in.close();
        System.out.println(solution(expression));
    }
}
