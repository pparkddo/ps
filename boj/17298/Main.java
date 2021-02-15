import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();

        Stack<Integer> stack = new Stack<>();
        for (int index = 0; index < n; index++) {
            int number = numbers[index];
            while (!stack.isEmpty() && numbers[stack.peek()] < number) {
                numbers[stack.pop()] = number;
            }
            stack.push(index);
        }
        while (!stack.isEmpty()) {
            numbers[stack.pop()] = -1;
        }
        System.out.println(Arrays.stream(numbers).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
    }
}
