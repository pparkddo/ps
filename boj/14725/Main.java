import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Main {

    private static final String DEPTH_DELIMITER = "--";

    private static String solution(Node node) {
        StringBuilder answer = new StringBuilder();
        traverse(node, 0, answer);
        return answer.toString().trim();
    }

    private static void traverse(Node node, int depth, StringBuilder result) {
        for (String each : node.children.keySet()) {
            result.append(getValueWithStep(each, depth)).append("\n");
            traverse(node.children.get(each), depth+1 ,result);
        }
    }

    private static String getValueWithStep(String value, int depth) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            s.append(DEPTH_DELIMITER);
        }
        return s.append(value).toString();
    }

    private static void insert(Node node, List<String> values, int index) {
        if (index == values.size()) {
            return;
        }
        String value = values.get(index);
        if (!node.children.containsKey(value)) {
            node.children.put(value, new Node());
        }
        insert(node.children.get(value), values, index+1);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        Node node = new Node();
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");
            int depth = Integer.parseInt(each[0]);
            List<String> values = new ArrayList<>();
            for (int index = 1; index <= depth; index++) {
                values.add(each[index]);
            }
            insert(node, values, 0);
        }
        in.close();
        System.out.println(solution(node));
    }
}

class Node {

    String value;
    TreeMap<String, Node> children = new TreeMap<>();
}
