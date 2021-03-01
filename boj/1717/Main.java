import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final int ROOT = -1;
    private static final String UNION = "0"; 
    private static final String FIND = "1"; 

    private static int find(int[] parents, int n) {
        if (parents[n] == ROOT) {
            return n;
        }
        return parents[n] = find(parents, parents[n]);
    }

    private static void union(int[] parents, int a, int b) {
        a = find(parents, a);
        b = find(parents, b);
        if (a == b) {
            return;
        }
        parents[b] = a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[] parents = new int[n+1];
        Arrays.fill(parents, ROOT);
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String[] command = in.readLine().split(" ");
            if (command[0].equals(UNION)) {
                union(parents, Integer.parseInt(command[1]), Integer.parseInt(command[2]));
            }
            if (command[0].equals(FIND)) {
                if (find(parents, Integer.parseInt(command[1])) == find(parents, Integer.parseInt(command[2]))) {
                    answer.append("YES").append("\n");
                }
                else {
                    answer.append("NO").append("\n");
                }
            }
        }
        in.close();
        System.out.println(answer.toString().trim());
    }
}
