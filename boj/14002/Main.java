import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    private static final int NOT_VISITED = -1;

    private static String solution(int n, int[] numbers) {
        int[] parents = new int[n];
        Arrays.fill(parents, NOT_VISITED);

        int[] lis = new int[n];
        int max = -1;
        int maxIndex = -1;
        for (int index = 0; index < lis.length; index++) {
            lis[index] = 1;
            for (int i = 0; i < lis.length; i++) {
                if (numbers[i] < numbers[index]) {
                    if (lis[i]+1 > lis[index]) {
                        parents[index] = i;
                    }
                    lis[index] = Math.max(lis[index], lis[i]+1);
                }
            }
            if (lis[index] > max) {
                max = lis[index];
                maxIndex = index;
            }
        }

        Stack<Integer> stack = new Stack<>();
        int index = maxIndex;
        stack.add(numbers[index]);
        while (true) {
            int parent = parents[index];
            if (parent == NOT_VISITED) {
                break;
            }
            stack.add(numbers[parent]);
            index = parent;
        }

        StringBuilder answer = new StringBuilder();
        answer.append(stack.size()).append("\n");
        while (!stack.isEmpty()) {
            answer.append(stack.pop()).append(" ");
        }

        return answer.toString();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] a = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();
        System.out.println(solution(n, a));
    }
}
