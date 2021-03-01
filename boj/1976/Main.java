import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    private static final int ROOT = -1;

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
        int n = Integer.parseInt(in.readLine());
        in.readLine();
        int[] parents = new int[n];
        Arrays.fill(parents, ROOT);
        for (int i = 0; i < parents.length; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < each.length; j++) {
                if (each[j].equals("1")) {
                    union(parents, i, j);
                }
            }
        }
        String[] itinerary = in.readLine().split(" ");
        in.close();

        Set<Integer> roots = new HashSet<>();
        for (String each : itinerary) {
            roots.add(find(parents, Integer.parseInt(each)-1));
        }
        System.out.println(roots.size() == 1 ? "YES" : "NO");
    }
}
