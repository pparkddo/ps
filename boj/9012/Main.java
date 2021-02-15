import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    private static final String YES = "YES";
    private static final String NO = "NO";

    private static boolean isCorrect(String[] parentheses) {
        Stack<String> stack = new Stack<>();
        for (String each: parentheses) {
            if (each.equals("(")) {
                stack.push(each);
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }
            stack.pop();
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String[] parentheses = in.readLine().split("");
            if (isCorrect(parentheses)) {
                answer.append(YES).append("\n");
            }
            else {
                answer.append(NO).append("\n");
            }
        }
        System.out.println(answer.toString().stripTrailing());
        in.close();
    }
}
