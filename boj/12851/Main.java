import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final int MAX = 100_000 + 1;

    private static String solution(int n, int k) {
        int[] counts = new int[MAX];
        Arrays.fill(counts, Integer.MAX_VALUE);

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(n, 0));
        counts[n] = 0;

        int answer = 0;
        while (!queue.isEmpty()) {
            final Node node = queue.poll();
            final int position = node.position;
            final int count = node.count;
            if (position == k) {
                answer++;
                continue;
            }
            int nextCount = count + 1;
            if (isValid(position+1) && nextCount <= counts[position+1]) {
                counts[position+1] = nextCount; 
                queue.add(new Node(position+1, count+1));
            }
            if (isValid(position-1) && nextCount <= counts[position-1]) {
                counts[position-1] = nextCount; 
                queue.add(new Node(position-1, count+1));
            }
            if (isValid(position*2) && nextCount <= counts[position*2]) {
                counts[position*2] = nextCount; 
                queue.add(new Node(position*2, count+1));
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(counts[k]).append("\n");
        result.append(answer);
        return result.toString();
    }

    private static boolean isValid(int position) {
        return position >= 0 && position < MAX;
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

class Node {

    int position;
    int count;

    Node(int position, int count) {
        this.position = position;
        this.count = count;
    }
}