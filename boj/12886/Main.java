import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        var in = new BufferedReader(new InputStreamReader(System.in));

        var abc = in.readLine().split(" ");
        int a = Integer.parseInt(abc[0]);
        int b = Integer.parseInt(abc[1]);
        int c = Integer.parseInt(abc[2]);

        System.out.println(solution(a, b, c));
    }

    private static int solution(int a, int b, int c) {
        boolean[][] visited = new boolean[1501][1501];

        int sum = a + b + c;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(Math.min(Math.min(a, b), c), Math.max(Math.max(a, b), c)));

        while (!queue.isEmpty()) {
            var node = queue.poll();

            int na = node.a;
            int nb = node.b;
            int nc = sum - node.a - node.b;

            if (na == nb && nb == nc) {
                return 1;
            }

            if (visited[node.a][node.b]) {
                continue;
            }
            visited[node.a][node.b] = true;

            int[] values = new int[3];
            for (int i = 0; i < 3; i++) {
                values[0] = na;
                values[1] = nb;
                values[2] = nc;
                for (int j = i + 1; j < 3; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (values[i] == values[j]) {
                        continue;
                    }
                    if (values[i] < values[j]) {
                        values[j] -= values[i];
                        values[i] += values[i];
                    } else {
                        values[i] -= values[j];
                        values[j] += values[j];
                    }

                    int minValue = Math.min(Math.min(values[0], values[1]), values[2]);
                    int maxValue = Math.max(Math.max(values[0], values[1]), values[2]);

                    if (visited[minValue][maxValue]) {
                        continue;
                    }
                    queue.add(new Node(minValue, maxValue));
                }
            }
        }

        return 0;
    }
}

class Node {

    int a;
    int b;

    Node(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "Node{" +
            "a=" + a +
            ", b=" + b +
            '}';
    }
}