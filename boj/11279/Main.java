import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(in.readLine());
            if (number == 0 && !queue.isEmpty()) {
                answer.append(queue.poll()).append("\n");
                continue;
            }
            if (number == 0 && queue.isEmpty()) {
                answer.append(0).append("\n");
                continue;
            }
            queue.add(number);
        }
        System.out.println(answer.toString().stripTrailing());
        in.close();
    }
}
