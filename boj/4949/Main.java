import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    private static boolean isBalanced(String[] s) {
        Stack<String> stack = new Stack<>();
        for (String each: s) {
            if (each.equals("(")) {
                stack.push("(");
                continue;
            }
            if (each.equals("[")) {
                stack.push("[");
                continue;
            }
            if (each.equals(")")) {
                if (stack.isEmpty()) {
                    return false;
                }
                if (!stack.pop().equals("(")) {
                    return false;
                };
            }
            if (each.equals("]")) {
                if (stack.isEmpty()) {
                    return false;
                }
                if (!stack.pop().equals("[")) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean isEnd(String[] s) {
        return s.length == 1 && s[0].equals(".");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answers = new StringBuilder();
        while (true) {
            String[] s = in.readLine().split("");
            if (isEnd(s)) {
                in.close();
                break;
            }
            String answer = isBalanced(s) ? "yes" : "no";
            answers.append(answer).append("\n");
        }
        System.out.println(answers.toString().stripTrailing());
    }
}
