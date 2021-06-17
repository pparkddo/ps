import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final int ROOT = -1;
    private static final int REMOVED = -2;

    private static int solution(int n, int[] parents, int removeIndex) {
        remove(parents, removeIndex);
        for (int index = 0; index < parents.length; index++) {
            if (isRemoved(index, parents)) {
                remove(parents, index);
            }
        }

        Map<Integer, List<Integer>> tree = buildTree(parents);

        int answer = 0;
        boolean[] visited = new boolean[n];
        for (int index = 0; index < n; index++) {
            if (visited[index]) {
                continue;
            }
            if (parents[index] != ROOT) {
                continue;
            }
            answer += dfs(index, tree, visited);
        }
        return answer;
    }

    private static void remove(int[] parents, int removeIndex) {
        parents[removeIndex] = REMOVED;
    }

    private static boolean isRemoved(int index, int[] parents) {
        return getRoot(index, parents) == REMOVED;
    }

    private static int getRoot(int index, int[] parents) {
        int parent = parents[index];
        if (parent == ROOT || parent == REMOVED) {
            return parent;
        }
        return getRoot(parent, parents);
    }

    private static int dfs(int index, Map<Integer, List<Integer>> tree, boolean[] visited) {
        if (visited[index]) {
            return 0;
        }
        visited[index] = true;
        int childCount = 0;
        if (tree.get(index).isEmpty()) {
            return 1;
        }
        for (Integer child : tree.get(index)) {
            childCount += dfs(child, tree, visited);
        }
        return childCount;
    }

    private static Map<Integer, List<Integer>> buildTree(int[] parents) {
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int index = 0; index < parents.length; index++) {
            tree.put(index, new ArrayList<>());
            for (int currentIndex = 0; currentIndex < parents.length; currentIndex++) {
                int parent = parents[currentIndex];
                if (parent == index) {
                    tree.get(index).add(currentIndex);
                }
            }
        }
        return tree;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        String[] p = in.readLine().split(" ");
        int[] parents = new int[n];
        for (int index = 0; index < p.length; index++) {
            parents[index] = Integer.parseInt(p[index]);
        }
        int removeIndex = Integer.parseInt(in.readLine());
        in.close();
        System.out.println(solution(n, parents, removeIndex));
    }
}
