import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static long[] numbers;
    private static long[] tree;

    private static long construct(int start, int end, int node) {
        if (start == end) {
            return tree[node] = numbers[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = construct(start, mid, node*2) + construct(mid+1, end, node*2+1);
    }

    private static long sum(int start, int end, int node, int left, int right) {
        if (start > right || end < left) {
            return 0;
        }
        if (start >= left && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2+1, left, right);
    }

    private static void update(int start, int end, int node, int index, long diff) {
        if (start > index || end < index) {
            return;
        }
        tree[node] += diff;
        if (start == end) {
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, node*2, index, diff);
        update(mid+1, end, node*2+1, index, diff);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nmk = in.readLine().split(" ");

        int n = Integer.parseInt(nmk[0]);
        numbers = new long[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Long.parseLong(in.readLine());
        }

        tree = new long[4 * n];
        int start = 0;
        int end = n - 1;
        int rootNode = 1;
        construct(start, end, rootNode);

        StringBuilder answer = new StringBuilder();
        int m = Integer.parseInt(nmk[1]);
        int k = Integer.parseInt(nmk[2]);
        for (int i = 0; i < m+k; i++) {
            String[] each = in.readLine().split(" ");
            int command = Integer.parseInt(each[0]);
            if (command == 1) {
                int index = Integer.parseInt(each[1]) - 1;
                long newNumber = Long.parseLong(each[2]);
                long diff = newNumber - numbers[index];
                numbers[index] = newNumber;
                update(start, end, rootNode, index, diff);
            }
            else if (command == 2) {
                int left = Integer.parseInt(each[1]) - 1;
                int right = Integer.parseInt(each[2]) - 1;
                answer.append(sum(start, end, rootNode, left, right)).append("\n");
            }
        }
        in.close();

        System.out.println(answer.toString().trim());
    }
}
