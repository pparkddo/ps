import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    private static final int SHARK = 9;
    private static final int BLANK = 0;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    private static boolean isValid(int row, int column, int[][] space) {
        return row >= 0 && row < space.length && column >= 0 && column < space[0].length;
    }

    private static List<Node> traverse(int initialRow, int initialColumn, int sharkSize, int[][] space) {
        List<Node> nodes = new ArrayList<>();
        
        boolean[][] visited = new boolean[space.length][space[0].length];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(initialRow, initialColumn, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            final int row = node.row;
            final int column = node.column;
            final int time = node.time;
            if (visited[row][column]) {
                continue;
            }
            visited[row][column] = true;
            if (space[row][column] != BLANK && space[row][column] < sharkSize && space[row][column] != SHARK) {
                nodes.add(node);
                continue;
            }
            for (int d = 0; d < dr.length; d++) {
                int nextRow = row + dr[d];
                int nextColumn = column + dc[d];
                if (!isValid(nextRow, nextColumn, space)) {
                    continue;
                }
                if (visited[nextRow][nextColumn]) {
                    continue;
                }
                int nextValue = space[nextRow][nextColumn];
                if (nextValue > sharkSize) {
                    continue;
                }
                if (!nodes.isEmpty() && nodes.get(0).time < time+1) {
                    continue;
                }
                Node nextNode = new Node(nextRow, nextColumn, time+1);
                queue.add(nextNode);
            }
        }

        return nodes;
    }

    private static Position getSharkPosition(int[][] space) {
        for (int i = 0; i < space.length; i++) {
            for (int j = 0; j < space[0].length; j++) {
                if (space[i][j] == SHARK) {
                    return new Position(i, j);
                }
            }
        }
        throw new IllegalArgumentException("invalid shark position");
    }

    private static Node getNextNode(List<Node> nodes) {
        Collections.sort(nodes);
        return nodes.get(0);
    }

    private static int solution(int n, int[][] space) {
        Position sharkPosition = getSharkPosition(space);
        int row = sharkPosition.row;
        int column = sharkPosition.column;
        int count = 0;
        int sharkSize = 2;

        int times = 0;
        while (true) {
            List<Node> nodes = traverse(row, column, sharkSize, space);
            if (nodes.isEmpty()) {
                break;
            }
            Node nextNode = getNextNode(nodes);
            space[row][column] = BLANK;
            row = nextNode.row;
            column = nextNode.column;
            times += nextNode.time;
            space[row][column] = SHARK;
            count++;
            if (count == sharkSize) {
                count = 0;
                sharkSize++;
            }
        }

        return times;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[][] space = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                space[i][j] = Integer.parseInt(each[j]);
            }
        }
        in.close();
        System.out.println(solution(n, space));
    }
}

class Node implements Comparable<Node> {

    int row;
    int column;
    int time;

    Node(int row, int column, int time) {
        this.row = row;
        this.column = column;
        this.time = time;
    }

    @Override
    public int compareTo(Node o) {
        if (time != o.time) {
            return Integer.compare(time, o.time);
        }
        if (row != o.row) {
            return Integer.compare(row, o.row);
        }
        return Integer.compare(column, o.column);
    }

    @Override
    public String toString() {
        return row + " " + column + " " + time;
    }
}

class Position {

    int row;
    int column;

    Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
