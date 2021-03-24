import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class NodeValue implements Comparable<NodeValue> {

    int id;
    int x;
    int y;

    NodeValue(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(NodeValue o) {
        if (this.y == o.y) {
            return Integer.compare(this.x, o.x);
        }
        return -Integer.compare(this.y, o.y);
    }

    @Override
    public String toString() {
        return this.id + " " + this.x + " " + this.y;
    }
}

class Node implements Comparable<Node> {

    Node left;
    Node right;
    NodeValue value;

    Node(NodeValue value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return this.left + " " + this.right + " " + this.value;
    }
}

class Solution {

    private void insertNode(Node parent, Node child) {
        if (parent.value.id == child.value.id) {
            return;
        }
        if (parent.value.x > child.value.x) {
            if (parent.left == null) {
                parent.left = child;
                return;
            }
            insertNode(parent.left, child);
        }
        if (parent.value.x < child.value.x) {
            if (parent.right == null) {
                parent.right = child;
                return;
            }
            insertNode(parent.right, child);
        }
    }

    private void preorder(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.value.id);
        preorder(node.left, result);
        preorder(node.right, result);
    }

    private void postorder(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.value.id);
    }

    public int[][] solution(int[][] nodeinfo) {
        List<Node> nodes = new ArrayList<>();
        for (int index = 0; index < nodeinfo.length; index++) {
            int[] each = nodeinfo[index];
            int id = index + 1;
            int x = each[0];
            int y = each[1];
            nodes.add(new Node(new NodeValue(id, x, y)));
        }

        Collections.sort(nodes);

        Node root = nodes.get(0);
        for (Node node : nodes) {
            insertNode(root, node);
        }

        List<Integer> preorderResult = new ArrayList<>();
        preorder(root, preorderResult);

        List<Integer> postorderResult = new ArrayList<>();
        postorder(root, postorderResult);

        int[][] answer = new int[2][nodes.size()];
        answer[0] = preorderResult.stream().mapToInt(i->i).toArray();
        answer[1] = postorderResult.stream().mapToInt(i->i).toArray();

        return answer;
    }
}
