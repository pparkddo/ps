import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static class Solution {

        private int n;
        private int m;
        private int[] combination;
        private StringBuilder answer;

        public Solution(int n, int m) {
            this.n = n;
            this.m = m;
            this.combination = new int[m];
            this.answer = new StringBuilder();
        }

        public void dfs(int depth, int start) {
            if (depth == m) {
                for (int each : combination) {
                    answer.append(each).append(" ");
                }
                answer.append("\n");
                return;
            }

            for (int number = start; number <= n; number++) {
                combination[depth] = number;
                dfs(depth+1, number+1);
            }
        }

        public String getAnswer() {
            return answer.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        in.close();

        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        Solution solution = new Solution(n, m);
        solution.dfs(0, 1);

        System.out.println(solution.getAnswer());
    }
}