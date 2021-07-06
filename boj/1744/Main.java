import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static int solution(int n, int[] numbers) {
        Arrays.sort(numbers);

        Deque<Integer> negatives = new LinkedList<>();
        Queue<Integer> zeros = new LinkedList<>();
        Queue<Integer> positives = new LinkedList<>();
        for (int number : numbers) {
            if (number < 0) {
                negatives.add(number);
            }
            else if (number == 0) {
                zeros.add(number);
            }
            else {
                positives.add(number);
            }
        }

        if ((negatives.size() % 2 == 1) && !zeros.isEmpty()) {
            if (negatives.size() == 1) {
                negatives.pollFirst();
            } else {
                negatives.pollLast();
            }
            zeros.poll();
        }

        int sum = getNegativesSum(negatives);
        sum += getPositivesSum(positives);
        return sum;
    }

    private static int getNegativesSum(Deque<Integer> negatives) {
        int sum = 0;
        if (negatives.size() % 2 == 1) {
            sum += negatives.pollLast();
        }
        while (!negatives.isEmpty()) {
            int s = 1;
            for (int i = 0; i < 2; i++) {
                s *= negatives.poll();
            }
            sum += s;
        }
        return sum;
    }

    private static int getPositivesSum(Queue<Integer> positives) {
        int sum = 0;
        while (!positives.isEmpty() && positives.peek() == 1) {
            sum += positives.poll();
        }
        if (positives.size() % 2 == 1) {
            sum += positives.poll();
        }
        while (!positives.isEmpty()) {
            int s = 1;
            for (int i = 0; i < 2; i++) {
                s *= positives.poll();
            }
            sum += s;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] numbers = new int[n];
        for (int index = 0; index < n; index++) {
            numbers[index] = Integer.parseInt(in.readLine());
        }
        in.close();
        System.out.println(solution(n, numbers));
    }
}
