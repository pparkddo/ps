import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    private static String removeBracket(String s) {
        return s.replaceAll("\\[", "").replaceAll("\\]", "");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder answers = new StringBuilder();
        int t = Integer.parseInt(in.readLine());
        for (int i = 0; i < t; i++) {
            String[] functions = in.readLine().split("");
            int n = Integer.parseInt(in.readLine());
            String[] numbers = in.readLine().split(",");
            Deque<Integer> deque = new LinkedList<>();
            for (int index = 0; index < n; index++) {
                if (index == 0 || index == n-1) {
                    deque.add(Integer.parseInt(removeBracket(numbers[index])));
                    continue;
                }
                deque.add(Integer.parseInt(numbers[index]));
            }

            boolean isError = false;
            boolean direction = false;  // false -> right, true -> left
            for (String function: functions) {
                if (function.equals("R")) {
                    direction = !direction;
                    continue;
                }
                if (deque.isEmpty()) {
                    isError = true;
                    break;
                }
                if (!direction) {
                    deque.pollFirst();
                    continue;
                }
                deque.pollLast();
            }

            if (isError) {
                answers.append("error").append("\n");
                continue;
            }

            StringBuilder answer = new StringBuilder();
            answer.append("[");
            while (!deque.isEmpty()) {
                int number = !direction ? deque.pollFirst() : deque.pollLast();
                answer.append(number).append(",");
            }
            if (answer.charAt(answer.length()-1) == ',') {
                answer.deleteCharAt(answer.length()-1);
            }
            answer.append("]");
            answers.append(answer).append("\n");
        }
        System.out.println(answers.toString().stripTrailing());
        in.close();
    }
}
