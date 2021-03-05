import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    private static boolean isValidParentheses(String[] parentheses) {
        Stack<String> stack = new Stack<>();
        for (String each : parentheses) {
            if (each.equals("(") || each.equals("[")) {
                stack.push(each);
            }
            if ((each.equals(")") || each.equals("]")) && stack.isEmpty()) {
                return false;
            }
            if (each.equals(")") && !stack.pop().equals("(")) {
                return false;
            }
            if (each.equals("]") && !stack.pop().equals("[")) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] parentheses = in.readLine().split("");
        in.close();

        if (!isValidParentheses(parentheses)) {
            System.out.println(0);
            return;
        }

        Stack<String> stack = new Stack<>();
        String previous = "";
        int answer = 0;
        int value = 1;
        for (String each : parentheses) {
            if (each.equals("(")) {
                stack.push(each);
                value *= 2;
            }
            if (each.equals("[")) {
                stack.push(each);
                value *= 3;
            }
            if (each.equals(")")) {
                if (previous.equals("(")) {
                    answer += value;
                }
                stack.pop();
                value /= 2;
            }
            if (each.equals("]")) {
                if (previous.equals("[")) {
                    answer += value;
                }
                stack.pop();
                value /= 3;
            }
            previous = each;
        }
        System.out.println(answer);
    }
}
