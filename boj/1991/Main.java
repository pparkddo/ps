import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static void preorder(Map<String, List<String>> tree, String node) {
        if (node == null) {
            return;
        }
        System.out.print(node);
        preorder(tree, tree.get(node).get(0));
        preorder(tree, tree.get(node).get(1));
    }

    private static void inorder(Map<String, List<String>> tree, String node) {
        if (node == null) {
            return;
        }
        inorder(tree, tree.get(node).get(0));
        System.out.print(node);
        inorder(tree, tree.get(node).get(1));
    }

    private static void postorder(Map<String, List<String>> tree, String node) {
        if (node == null) {
            return;
        }
        postorder(tree, tree.get(node).get(0));
        postorder(tree, tree.get(node).get(1));
        System.out.print(node);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        Map<String, List<String>> tree = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] each = in.readLine().split(" ");

            List<String> nodes = new ArrayList<>();
            nodes.add(each[1].equals(".") ? null : each[1]);
            nodes.add(each[2].equals(".") ? null : each[2]);

            tree.put(each[0], nodes);
        }
        in.close();

        preorder(tree, "A");
        System.out.println();
        inorder(tree, "A");
        System.out.println();
        postorder(tree, "A");
    }
}
