import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    private static int solution(String s) {
        int reverseCount = 0;
        int openCount = 0;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '}') {
                if (stack.isEmpty()) {
                    reverseCount++;
                    openCount++;
                    stack.push('{');
                    continue;
                }
                if (stack.peek() == '}') {
                    reverseCount++;
                    openCount++;
                    stack.push('{');
                    continue;
                }
                stack.pop();
            }
            else if (c == '{') {
                openCount++;
                stack.push(c);
            }
        }
        int correctOpenCount = s.length() / 2;
        return reverseCount + Math.abs(correctOpenCount - openCount);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answers = new StringBuilder();
        int number = 1;
        while (true) {
            String s = in.readLine();
            if (s.charAt(0) == '-') {
                break;
            }
            answers.append(number++).append(". ").append(solution(s)).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}
