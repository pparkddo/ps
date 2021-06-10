import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    private static final int MAX = 100_001;

    private static int solution(int n, int k) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(n, 0));

        boolean[] times = new boolean[MAX];

        int answer = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            final int x = node.x;
            final int time = node.time;
            if (times[x]) {
                continue;
            }
            times[x] = true;
            if (x == k) {
                answer = time;
                break;
            }
            int doubled = x * 2;
            if (isValid(doubled) && !times[doubled] && doubled != 0) {
                queue.add(new Node(doubled, time));
            }
            if (isValid(x-1) && !times[x-1]) {
                queue.add(new Node(x-1, time+1));
            }
            if (isValid(x+1) && !times[x+1]) {
                queue.add(new Node(x+1, time+1));
            }
        }

        return answer;
    }

    private static boolean isValid(int x) {
        return x >= 0 && x < MAX;
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

class Node implements Comparable<Node> {

    int x;
    int time;

    Node(int x, int time) {
        this.x = x;
        this.time = time;
    }

    @Override
    public String toString() {
        return x + " " + time;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(time, o.time);
    }
}