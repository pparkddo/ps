import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class Node {

    int row;
    int column;

    Node(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object obj) {
        Node node = (Node) obj;
        return row == node.row && column == node.column;
    }
    
    @Override
    public String toString() {
        return this.row + " " + this.column;
    }
}

class Snake {

    int direction = 1;  // U, R, D, L
    Deque<Node> nodes = new LinkedList<>();
    
    Snake() {
        nodes.add(new Node(0, 0));
    }

    public void move(Node nextNode) {
        nodes.addLast(nextNode);
    }
    
    public void turn(String turnString) {
        int turnValue = turnString.equals("D") ? 1 : -1;
        int tempDirection = direction + turnValue;
        if (tempDirection == -1) {
            direction = 3;
            return;
        }
        if (tempDirection == 4) {
            direction = 0;
            return;
        }
        direction = tempDirection;
    }

    public void removeTail() {
        nodes.poll();
    }

    public Node getNextNode() {
        Node head = nodes.getLast();
        switch (direction) {
            case 0:
                return new Node(head.row-1, head.column);
            case 1:
                return new Node(head.row, head.column+1);
            case 2:
                return new Node(head.row+1, head.column);
            case 3:
                return new Node(head.row, head.column-1);
        }
        return null;
    }

    public boolean isCrashed(boolean[][] board, Node nextNode) {
        return (
            nextNode.row < 0
            || nextNode.row >= board.length
            || nextNode.column < 0
            || nextNode.column >= board[0].length
        );
    }

    public boolean isSelfTouched(Node nextNode) {
        return nodes.contains(nextNode);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int k = Integer.parseInt(in.readLine());
        boolean[][] board = new boolean[n][n];
        for (int i = 0; i < k; i++) {
            String[] each = in.readLine().split(" ");
            int rowIndex = Integer.parseInt(each[0]) - 1;
            int columnIndex = Integer.parseInt(each[1]) - 1;
            board[rowIndex][columnIndex] = true;
        }
        int l = Integer.parseInt(in.readLine());
        Map<Integer, String> turns = new HashMap<>();
        for (int i = 0; i < l; i++) {
            String[] each = in.readLine().split(" ");
            turns.put(Integer.parseInt(each[0]), each[1]);
        }
        in.close();

        Snake snake = new Snake();
        int time = 0;
        while (true) {
            time++;
            Node nextNode = snake.getNextNode();
            if (snake.isCrashed(board, nextNode) || snake.isSelfTouched(nextNode)) {
                break;
            }
            snake.move(nextNode);
            if (!board[nextNode.row][nextNode.column]) {
                snake.removeTail();
            }
            board[nextNode.row][nextNode.column] = false;
            if (turns.containsKey(time)) {
                snake.turn(turns.get(time));
            }
        }

        System.out.println(time);
    }
}
