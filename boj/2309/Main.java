import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final int NUMBER_OF_INPUT = 9;

    private static void dfs(int start, int depth, int sum, boolean[] visited, List<Integer> answer, int[] numbers) {
        if (!answer.isEmpty()) {
            return;
        }
        if (depth == 7) {
            if (sum == 100) {
                for (int index = 0; index < visited.length; index++) {
                    if (visited[index]) {
                        answer.add(numbers[index]);
                    }
                }
                answer.sort(Comparator.naturalOrder());
            }
            return;
        }
        for (int index = start; index < visited.length; index++) {
            visited[index] = true;
            dfs(index+1, depth+1, sum+numbers[index], visited, answer, numbers);
            visited[index] = false;
        }
    }

    private static String solution(int[] numbers) {
        List<Integer> answer = new ArrayList<>();
        dfs(0, 0, 0, new boolean[NUMBER_OF_INPUT], answer, numbers);;
        return answer.stream().map(String::valueOf).collect(Collectors.joining("\n"));
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = new int[NUMBER_OF_INPUT];
        for (int index = 0; index < numbers.length; index++) {
            numbers[index] = Integer.parseInt(in.readLine());
        }
        System.out.println(solution(numbers));
    }
}
