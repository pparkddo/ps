import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final int ROOT = -1;

    private static int find(int[] parents, int n) {
        if (parents[n] <= ROOT) {
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
        parents[a] += parents[b];
        parents[b] = a;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int f = Integer.parseInt(in.readLine());
            int[] parents = new int[200001];
            Arrays.fill(parents, ROOT);
            int index = 0;
            Map<String, Integer> names = new HashMap<>();
            for (int j = 0; j < f; j++) {
                String[] each = in.readLine().split(" ");
                String a = each[0];
                String b = each[1];
                if (!names.containsKey(a)) {
                    names.put(a, index++);
                }
                if (!names.containsKey(b)) {
                    names.put(b, index++);
                }
                union(parents, names.get(a), names.get(b));
                int count = -parents[find(parents, names.get(a))]; 
                answers.append(count).append("\n");
            }
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}
