import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int testCase = 0; testCase < t; testCase++) {
            int n =Integer.parseInt(in.readLine());
            Map<Integer, List<Integer>> tree = new HashMap<>();
            for (int node = 1; node <= n; node++) {
                tree.put(node, new ArrayList<>());
            }
            for (int index = 0; index < n-1; index++) {
                String[] pc = in.readLine().split(" ");
                int parent = Integer.parseInt(pc[0]);
                int child = Integer.parseInt(pc[1]);
                tree.get(parent).add(child);
            }
            String[] ab = in.readLine().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);
            answers.append(new Solution(n, tree).getAnswer(a, b)).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}

class Solution {

    private int n;
    private Map<Integer, List<Integer>> tree;
    private int answer = NOT_SOLVED;
    private static final int NOT_SOLVED = -1;

    Solution(int n, Map<Integer, List<Integer>> tree) {
        this.n = n;
        this.tree = tree;
    }

    public int getAnswer(int a, int b) {
        int root = getRoot();
        traverse(root, a, b);
        return answer;
    }

    private int getRoot() {
        boolean[] count = new boolean[n+1];
        for (List<Integer> children : tree.values()) {
            for (Integer each : children) {
                count[each] = true;
            }
        }
        for (int node = 1; node < count.length; node++) {
            if (count[node] == false) {
                return node;
            }
        }
        throw new IllegalArgumentException("This is not a tree");
    }

    private int traverse(int node, int a, int b) {
        if (!tree.containsKey(node)) {
            return 0;
        }
        int matchCount = 0;
        if (node == a || node == b) {
            matchCount++;
        }
        for (int each : tree.get(node)) {
            matchCount += traverse(each, a, b);
        }
        if (answer == NOT_SOLVED && matchCount == 2) {
            answer = node;
        }
        return matchCount;
    }
}