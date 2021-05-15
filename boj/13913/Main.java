import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

    private static final int MAX = 100_001;
    private static final int NOT_VISITED = -1;

    private static boolean isAvailable(int number) {
        return number >= 0 && number < MAX;
    }

    private static String solution(int n, int k) {
        int[] parents = new int[MAX];
        Arrays.fill(parents, NOT_VISITED);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == k) {
                break;
            }
            if (isAvailable(node-1) && parents[node-1] == NOT_VISITED) {
                queue.add(node-1);
                parents[node-1] = node;
            }
            if (isAvailable(node+1) && parents[node+1] == NOT_VISITED) {
                queue.add(node+1);
                parents[node+1] = node;
            }
            if (isAvailable(node*2) && parents[node*2] == NOT_VISITED) {
                queue.add(node*2);
                parents[node*2] = node;
            }
        }

        Stack<Integer> stack = new Stack<>();
        int node = k;
        stack.add(node);
        while (node != n) {
            int parent = parents[node];
            stack.add(parent);
            node = parent;
        }

        StringBuilder answer = new StringBuilder();
        answer.append(stack.size()-1).append("\n");
        while (!stack.isEmpty()) {
            answer.append(stack.pop()).append(" ");
        }

        return answer.toString();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = in.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        in.close();
        System.out.println(solution(n, k));
    }
}
