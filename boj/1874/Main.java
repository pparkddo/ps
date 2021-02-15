import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int lastUsedNumber = 0;
        boolean isPossible = true;
        StringBuilder answer = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= n; i++) {
            int number = Integer.parseInt(in.readLine());
            for (int j = lastUsedNumber+1; j <= number; j++) {
                stack.push(j);
                lastUsedNumber = j;
                answer.append("+").append("\n");
            }
            if (stack.pop() != number) {
                isPossible = false;
                break;
            }
            answer.append("-").append("\n");
        }
        if (isPossible) {
            System.out.println(answer.toString().stripTrailing());
        }
        else {
            System.out.println("NO");
        }
        in.close();
    }
}
