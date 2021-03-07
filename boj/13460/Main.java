import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Marble {

    int row;
    int column;
    boolean out = false;
    private static final String HOLE = "O";
    private static final String WALL = "#";

    Marble(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void move(String[][] board, Marble marble, int direction) {
        switch (direction) {
            case 0:
                this.moveUp(board, marble);
                break;
            case 1:
                this.moveDown(board, marble);
                break;
            case 2:
                this.moveLeft(board, marble);
                break;
            case 3:
                this.moveRight(board, marble);
                break;
        }
    }

    public void moveUp(String[][] board, Marble marble) {
        while (true) {
            this.row--;
            if (board[this.row][this.column].equals(WALL)) {
                this.row++;
                break;
            }
            if (this.equals(marble) && !this.out && !marble.out) {
                this.row++;
                break;
            }
            if (board[this.row][this.column].equals(HOLE)) {
                this.out = true;
                break;
            }
        }
    }

    public void moveDown(String[][] board, Marble marble) {
        while (true) {
            this.row++;
            if (board[this.row][this.column].equals(WALL)) {
                this.row--;
                break;
            }
            if (this.equals(marble) && !this.out && !marble.out) {
                this.row--;
                break;
            }
            if (board[this.row][this.column].equals(HOLE)) {
                this.out = true;
                break;
            }
        }
    }

    public void moveLeft(String[][] board, Marble marble) {
        while (true) {
            this.column--;
            if (board[this.row][this.column].equals(WALL)) {
                this.column++;
                break;
            }
            if (this.equals(marble) && !this.out && !marble.out) {
                this.column++;
                break;
            }
            if (board[this.row][this.column].equals(HOLE)) {
                this.out = true;
                break;
            }
        }
    }

    public void moveRight(String[][] board, Marble marble) {
        while (true) {
            this.column++;
            if (board[this.row][this.column].equals(WALL)) {
                this.column--;
                break;
            }
            if (this.equals(marble) && !this.out && !marble.out) {
                this.column--;
                break;
            }
            if (board[this.row][this.column].equals(HOLE)) {
                this.out = true;
                break;
            }
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        Marble marble = (Marble) obj;
        return this.row == marble.row && this.column == marble.column;
    }
}

class Node {

    Marble red;
    Marble blue;
    int depth;

    Node(Marble red, Marble blue, int depth) {
        this.red = red;
        this.blue = blue;
        this.depth = depth;
    }
}

public class Main {

    private static final String RED = "R";
    private static final String BLUE = "B";
    private static final int MAX = 10;
    private static final int[] directions = {0, 1, 2, 3}; // up, down, left, right
    private static String[][] board;

    private static boolean isOtherExists(Marble marble, Marble other, int direction) {
        switch (direction) {
            case 0:
                return isOtherOnUp(marble, other);
            case 1:
                return isOtherOnDown(marble, other);
            case 2:
                return isOtherOnLeft(marble, other);
            case 3:
                return isOtherOnRight(marble, other);
        }
        throw new IllegalArgumentException();
    }

    private static boolean isOtherOnUp(Marble marble, Marble other) {
        return marble.row > other.row && marble.column == other.column;
    }

    private static boolean isOtherOnDown(Marble marble, Marble other) {
        return marble.row < other.row && marble.column == other.column;
    }

    private static boolean isOtherOnLeft(Marble marble, Marble other) {
        return marble.row == other.row && marble.column > other.column;
    }

    private static boolean isOtherOnRight(Marble marble, Marble other) {
        return marble.row == other.row && marble.column < other.column;
    }

    private static int bfs(Marble red, Marble blue) {
        int answer = Integer.MAX_VALUE;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(new Marble(red.row, red.column), new Marble(blue.row, blue.column), 0));
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            Marble redMarble = node.red;
            Marble blueMarble = node.blue;
            int depth = node.depth;

            if (depth > MAX) {
                continue;
            }
            if (blueMarble.out) {
                continue;
            }
            if (redMarble.out) {
                answer = Math.min(answer, depth);
                continue;
            }

            for (int direction = 0; direction < directions.length; direction++) {
                Marble newRedMarble = new Marble(redMarble.row, redMarble.column);
                Marble newBlueMarble = new Marble(blueMarble.row, blueMarble.column);
                if (!isOtherExists(newRedMarble, newBlueMarble, direction)) {
                    newRedMarble.move(board, newBlueMarble, direction);
                    newBlueMarble.move(board, newRedMarble, direction);
                }
                else {
                    newBlueMarble.move(board, newRedMarble, direction);
                    newRedMarble.move(board, newBlueMarble, direction);
                }
                queue.add(new Node(newRedMarble, newBlueMarble, depth+1));
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        board = new String[n][m];
        Marble red = null;
        Marble blue = null;
        for (int i = 0; i < board.length; i++) {
            String[] each = in.readLine().split("");
            for (int j = 0; j < board[0].length; j++) {
                String value = each[j];
                if (value.equals(RED)) {
                    red = new Marble(i, j);
                } 
                if (value.equals(BLUE)) {
                    blue = new Marble(i, j);
                } 
                board[i][j] = value;
            }
        }
        in.close();

        int answer = bfs(red, blue);
        System.out.println(answer);
    }
}
