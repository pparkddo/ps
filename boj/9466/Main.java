import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static boolean[] visited;
    private static boolean[] finished;
    private static int count;

    private static void updateCount(int[] numbers, int nextNumber, int number) {
        count++;
        if (nextNumber == number) {
            return;
        }
        updateCount(numbers, numbers[nextNumber], number);
    }

    private static void dfs(int[] numbers, int number) {
        visited[number] = true;
        int nextNumber = numbers[number];
        if (!visited[nextNumber]) {
            dfs(numbers, nextNumber);
        }
        else if (!finished[nextNumber]) {
            updateCount(numbers, nextNumber, number);
        }
        finished[number] = true;
    }

    private static int solution(int n, int[] numbers) {
        visited = new boolean[n+1];
        finished = new boolean[n+1];
        count = 0;
        for (int number = 1; number <= n; number++) {
            if (visited[number]) {
                continue;
            }
            dfs(numbers, number);
        }
        return n - count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int testCase = 0; testCase < t; testCase++) {
            int n = Integer.parseInt(in.readLine());
            int[] numbers = new int[n+1];
            String[] each = in.readLine().split(" ");
            for (int i = 1; i <= n; i++) {
                numbers[i] = Integer.parseInt(each[i-1]);
            }
            answers.append(solution(n, numbers)).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}
