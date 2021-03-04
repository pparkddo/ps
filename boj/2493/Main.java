import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

class Tower {

    int sequence;
    int height;

    Tower(int sequence, int height) {
        this.sequence = sequence;
        this.height = height;
    }

    @Override
    public String toString() {
        return this.sequence + " " + this.height;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] heights = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();

        StringBuilder answer = new StringBuilder();
        Stack<Tower> stack = new Stack<>();
        for (int index = 0; index < n; index++) {
            int sequence = index + 1;
            int height = heights[index];
            while (!stack.isEmpty() && stack.peek().height < height) {
                stack.pop();
            }
            answer.append(stack.isEmpty() ? 0 : stack.peek().sequence).append(" ");
            stack.push(new Tower(sequence, height));
        }
        System.out.println(answer);
    }
}