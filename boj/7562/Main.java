import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Node {
    
    int row;
    int column;
    int depth;

    Node(int row, int column, int depth) {
        this.row = row;
        this.column = column;
        this.depth = depth;
    }
}

class BFS {

    private int l;
    private boolean[][] visited;
    private int initialRow;
    private int initialColumn;
    private int targetRow;
    private int targetColumn;
    private int minimumCount = Integer.MAX_VALUE;
    private int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    private int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};

    BFS(int l) {
        this.l = l;
        this.visited = new boolean[l][l];
    }

    public void setInitial(int row, int column) {
        this.initialRow = row;
        this.initialColumn = column;
    }

    public void setTarget(int row, int column) {
        this.targetRow = row;
        this.targetColumn = column;
    }

    public void run() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(this.initialRow, this.initialColumn, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int row = node.row;
            int column = node.column;
            int depth = node.depth;
            if (row == this.targetRow && column == this.targetColumn) {
                this.minimumCount = depth;
                break;
            }
            if (visited[row][column]) {
                continue;
            }
            visited[row][column] = true;

            for (int i = 0; i < this.dx.length; i++) {
                int nextRow = row + this.dy[i];
                int nextColumn = column + this.dx[i];
                if (!isPossible(nextRow, nextColumn)) {
                    continue;
                }
                queue.add(new Node(nextRow, nextColumn, depth+1));
            }
        }
    }

    public int getMinimumCount() {
        return this.minimumCount;
    }

    private boolean isPossible(int row, int column) {
        if (row < 0 || row >= this.l) {
            return false;
        }
        if (column < 0 || column >= this.l) {
            return false;
        }
        return true;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int l = Integer.parseInt(in.readLine());
            BFS dfs = new BFS(l);
            String[] initial = in.readLine().split(" ");
            dfs.setInitial(Integer.parseInt(initial[0]), Integer.parseInt(initial[1]));
            String[] target = in.readLine().split(" ");
            dfs.setTarget(Integer.parseInt(target[0]), Integer.parseInt(target[1]));
            dfs.run();
            answers.append(dfs.getMinimumCount()).append("\n");
        }
        System.out.println(answers.toString().trim());
        in.close();
    }
}
