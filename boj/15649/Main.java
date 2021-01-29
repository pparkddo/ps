import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final StringBuilder answer = new StringBuilder();

    private static void dfs(int n, int m, int depth, int[] node, boolean[] isUsed) {
        if (depth == m) {
            for (int each : node) {
                answer.append(each).append(" ");
            }
            answer.append("\n");
            return;
        }

        for (int number = 1; number < n+1; number++) {
            int index = number - 1;
            if (isUsed[index]) {
                continue;
            }
            node[depth] = number;
            isUsed[index] = true; 
            dfs(n, m, depth+1, node, isUsed);
            isUsed[index] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        in.close();

        StringTokenizer st = new StringTokenizer(s, " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int depth = 0;
        int[] node = new int[m];
        boolean[] isUsed = new boolean[n];

        dfs(n, m, depth, node, isUsed);
        System.out.println(answer.toString());
    }
}