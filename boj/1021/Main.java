import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine().split(" ")[0]);
        int numbers[] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();

        Deque<Integer> deque = new LinkedList<>(IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList()));
        int count = 0;
        for (int number: numbers) {
            int tempCount = 0;
            while (deque.peek() != number) {
                deque.addFirst(deque.pollLast());
                tempCount += 1;
            }
            count += Math.min(tempCount, deque.size()-tempCount);
            deque.pop();
        }
        System.out.println(count);
    }
}
