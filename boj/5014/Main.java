import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final int UNREACHABLE = -1;

    private static String solution(int f, int s, int g, int u, int d) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(s, 0));

        int answer = UNREACHABLE;
        boolean[] visited = new boolean[f+1];
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            final int floor = node.floor;
            final int count = node.count;
            if (visited[floor]) {
                continue;
            }
            visited[floor] = true;
            if (floor == g) {
                answer = count;
                break;
            }
            int upperFloor = floor + u;
            if (upperFloor <= f) {
                queue.add(new Node(upperFloor, count+1));
            }
            int lowerFloor = floor - d;
            if (lowerFloor >= 1) {
                queue.add(new Node(lowerFloor, count+1));
            }
        }

        return answer == UNREACHABLE ? "use the stairs" : String.valueOf(answer);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] fsgud = in.readLine().split(" ");
        int f = Integer.parseInt(fsgud[0]);
        int s = Integer.parseInt(fsgud[1]);
        int g = Integer.parseInt(fsgud[2]);
        int u = Integer.parseInt(fsgud[3]);
        int d = Integer.parseInt(fsgud[4]);
        in.close();
        System.out.println(solution(f, s, g, u, d));
    }
}

class Node {

    int floor;
    int count;

    Node(int floor, int count) {
        this.floor = floor;
        this.count = count;
    }
}
