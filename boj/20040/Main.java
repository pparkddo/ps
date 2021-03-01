import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final int ROOT = -1;

    private static int find(int[] parents, int n) {
        if (parents[n] == ROOT) {
            return n;
        }
        return parents[n] = find(parents, parents[n]);
    }

    private static boolean union(int[] parents, int a, int b) {
        a = find(parents, a);
        b = find(parents, b);
        if (a == b) {
            return false;
        }
        parents[b] = a;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[] parents = new int[n];
        Arrays.fill(parents, ROOT);
        int answer = 0;
        for (int turn = 1; turn <= m; turn++) {
            String[] each = in.readLine().split(" ");
            int a = Integer.parseInt(each[0]);
            int b = Integer.parseInt(each[1]);
            if (!union(parents, a, b)) {
                answer = turn;
                break;
            }
        }
        in.close();
        System.out.println(answer);
    }
}
