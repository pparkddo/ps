import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {

    private static final String ROOT = "ROOT";
    private static final String STEP_DELIMITER = "--";

    private static String solution(Node node) {
        StringBuilder answer = new StringBuilder();
        traverse(ROOT, node, answer);
        return answer.toString().trim();
    }

    private static void traverse(String key, Node node, StringBuilder result) {
        if (!key.equals(ROOT)) {
            result.append(key).append("\n");
        }
        if (node.nodes.isEmpty()) {
            return;
        }
        for (String each : node.nodes.keySet()) {
            traverse(each, node.nodes.get(each), result);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        Node rootNode = new Node(ROOT);
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            int step = Integer.parseInt(each[0]);
            Node previous = rootNode;
            for (int j = 1; j <= step; j++) {
                StringBuilder sb = new StringBuilder();
                for (int s = 1; s < j; s++) {
                    sb.append(STEP_DELIMITER);
                }
                sb.append(each[j]);
                String value = sb.toString();
                if (!previous.nodes.containsKey(value)) {
                    previous.nodes.put(value, new Node(value));
                }
                previous = previous.nodes.get(value);
            }
        }
        in.close();
        System.out.println(solution(rootNode));
    }
}

class Node {

    String value;
    TreeMap<String, Node> nodes = new TreeMap<>();

    Node(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + ": " + nodes;
    }
}
