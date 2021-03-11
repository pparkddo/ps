import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = in.readLine().split(" ");
        in.close();

        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        Deque<Integer> deque = new LinkedList<>(IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList()));

        StringBuilder answer = new StringBuilder().append("<");
        while (!deque.isEmpty()) {
            for (int i = 0; i < k-1; i++) {
                deque.addLast(deque.pop());
            }
            answer.append(deque.pop()).append(", ");
        }
        System.out.println(answer.delete(answer.length()-2, answer.length()).append(">"));
    }
}
