import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {

    private static final char UP = 'U';
    private static final char DOWN = 'D';
    private static final char CLEAR = 'C';
    private static final char ROLLBACK = 'Z';

    public String solution(int n, int k, String[] cmd) {
        List<Node> nodes = getNodes(n);
        Stack<Node> deleted = new Stack<>();
        Node currentNode = nodes.get(k);
        for (String command : cmd) {
            char c = parseCommand(command);
            if (c == UP) {
                currentNode = moveUp(currentNode, parseArgument(command));
            }
            else if (c == DOWN) {
                currentNode = moveDown(currentNode, parseArgument(command));
            }
            else if (c == CLEAR) {
                currentNode.deleted = true;
                deleted.add(currentNode);
                currentNode = clear(currentNode);
            }
            else if (c == ROLLBACK) {
                Node restored = deleted.pop();
                restored.deleted = false;
                rollback(restored);
            }
        }
        return nodesToString(nodes);
    }

    private String nodesToString(List<Node> nodes) {
        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            sb.append(node.deleted ? 'X' : 'O');
        }
        return sb.toString();
    }

    private void rollback(Node restored) {
        Node prevNode = restored.prev;
        Node nextNode = restored.next;
        if (prevNode != null) {
            prevNode.next = restored;
        }
        if (nextNode != null) {
            nextNode.prev = restored;
        }
    }

    private Node clear(Node currentNode) {
        Node prevNode = currentNode.prev;
        Node nextNode = currentNode.next;
        if (nextNode != null) {
            nextNode.prev = prevNode;
        }
        if (prevNode != null) {
            prevNode.next = nextNode;
        }

        if (nextNode != null) {
            return nextNode;
        }
        else {
            return prevNode;
        }
    }

    private Node moveUp(Node currentNode, int argument) {
        for (int i = 0; i < argument; i++) {
            currentNode = currentNode.prev;
        }
        return currentNode;
    }

    private Node moveDown(Node currentNode, int argument) {
        for (int i = 0; i < argument; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    private List<Node> getNodes(int n) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(0));
        for (int index = 1; index < n; index++) {
            Node node = new Node(index);
            Node prevNode = nodes.get(index-1);
            node.prev = prevNode;
            prevNode.next = node;
            nodes.add(node);
        }
        return nodes;
    }

    private char parseCommand(String command) {
        return command.charAt(0);
    }

    private int parseArgument(String command) {
        return Integer.parseInt(command.split(" ")[1]);
    }
}

class Node {

    Node prev = null;
    Node next = null;
    int value;
    boolean deleted = false;

    Node(int value) {
        this.value = value;
    }
}