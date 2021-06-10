import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    private static long solution(int n, int[] heights) {
        long answer = 0;

        Stack<Integer> leftIndexes = new Stack<>();
        int rightIndex = 0;
        for (; rightIndex < heights.length; rightIndex++) {
            while (!leftIndexes.isEmpty() && heights[leftIndexes.peek()] > heights[rightIndex]) {
                long width = getWidth(leftIndexes, rightIndex, heights);
                answer = Math.max(answer, width);
            }
            leftIndexes.push(rightIndex);
        }

        while (!leftIndexes.isEmpty())  {
            long width = getWidth(leftIndexes, rightIndex, heights);
            answer = Math.max(answer, width);
        }

        return answer;
    }

    private static long getWidth(Stack<Integer> leftIndexes, int rightIndex, int[] heights) {
        int height = heights[leftIndexes.pop()];
        if (leftIndexes.isEmpty()) {
            return calculateWidth(0, rightIndex, height);
        }
        return calculateWidth(leftIndexes.peek()+1, rightIndex, height);
    }

    private static long calculateWidth(int leftIndex, int rightIndex, int height) {
        return (rightIndex - leftIndex) * (long) height;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answers = new StringBuilder();
        while (true) {
            String each = in.readLine();
            if (each.equals("0")) {
                break;
            }
            String[] numbers = each.split(" ");
            int n = Integer.parseInt(numbers[0]);
            int[] heights = new int[n];
            for (int index = 0; index < n; index++) {
                heights[index] = Integer.parseInt(numbers[index+1]);
            }
            answers.append(solution(n, heights)).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}
