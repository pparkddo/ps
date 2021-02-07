import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Position {

    int x;
    int count;

    Position(int x, int count) {
        this.x = x;
        this.count = count;
    }

    public int getX() {
        return x;
    }

    public int getCount() {
        return count;
    }
}

public class Main {

    private static int k;
    private static final int MAX = 100000;
    private static int answer = MAX;

    private static void bfs(int n) {
        Queue<Position> queue = new LinkedList<>();
        Map<Integer, Integer> visited = new HashMap<>();
        queue.add(new Position(n, 0));
        while (!queue.isEmpty()) {
            Position position = queue.poll();
            int x = position.getX();
            int count = position.getCount();
            if (visited.containsKey(x) && count > visited.get(x)) {
                continue;
            }
            if (x < 0 || x > MAX) {
                continue;
            }
            if (count > answer) {
                continue;
            }
            if (x == k) {
                answer = count;
                continue;
            }
            visited.put(x, count);
            queue.add(new Position(x-1, count+1));
            queue.add(new Position(x+1, count+1));
            queue.add(new Position(x*2, count+1));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = in.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        k = Integer.parseInt(nk[1]);
        in.close();

        bfs(n);

        System.out.println(answer);
    }
}
