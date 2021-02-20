import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Node {

    int x;
    int y;
    int z;
    int depth;

    Node(int x, int y, int z, int depth) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.depth = depth;
    }
}

public class Main {

    private static int m;
    private static int n;
    private static int h;
    private static int[] dx = {0, 0, -1, 1, 0, 0};
    private static int[] dy = {0, 0, 0, 0, -1, 1};
    private static int[] dz = {1, -1, 0, 0, 0, 0};

    private static boolean isPossible(int x, int y, int z) {
        if (x < 0 || x >= m) {
            return false;
        }
        if (y < 0 || y >= n) {
            return false;
        }
        if (z < 0 || z >= h) {
            return false;
        } 
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] mnh = in.readLine().split(" ");
        m = Integer.parseInt(mnh[0]);
        n = Integer.parseInt(mnh[1]);
        h = Integer.parseInt(mnh[2]);

        int[][][] box = new int[h][n][m];
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                String[] each = in.readLine().split(" ");
                for (int k = 0; k < m; k++) {
                    int value = Integer.parseInt(each[k]);
                    box[i][j][k] = value;
                    if (value == 1) {
                        queue.add(new Node(k, j, i, 0));
                    }
                }
            }
        }
        in.close();

        int answer = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            int z = node.z;
            int depth = node.depth;

            answer = Math.max(answer, depth);

            for (int i = 0; i < dx.length; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                int nextZ = z + dz[i];

                if (!isPossible(nextX, nextY, nextZ)) {
                    continue;
                }

                if (box[nextZ][nextY][nextX] != 0) {
                    continue;
                }

                queue.add(new Node(nextX, nextY, nextZ, depth+1));
                box[nextZ][nextY][nextX] = 1;
            }
        }

        boolean isComplete = true;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (box[i][j][k] == 0) {
                        isComplete = false;
                    }
                }
            }
        }

        if (isComplete) {
            System.out.println(answer);
        }
        else {
            System.out.println(-1);
        }
    }
}
