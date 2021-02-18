import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        PriorityQueue<Integer> lower = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> upper = new PriorityQueue<>();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(in.readLine());
            upper.add(number);
            if (lower.size() == upper.size()) {
                if (upper.peek()< lower.peek()) {
                    int up = upper.poll();
                    int low = lower.poll();
                    lower.add(up);
                    upper.add(low);
                }
            }
            else {
                lower.add(upper.poll());
            }
            answer.append(lower.peek()).append("\n");
        }
        System.out.println(answer.toString().stripTrailing());
        in.close();
    }
}
