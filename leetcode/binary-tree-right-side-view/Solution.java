import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

    public List<Integer> rightSideView(TreeNode root) {
        return getRightSideView(root);
    }

    private List<Integer> getRightSideView(TreeNode root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(root, 1));

        List<Integer> values = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            TreeNode treeNode = node.treeNode;
            int depth = node.depth;
            if (treeNode == null) {
                continue;
            }
            if (values.size() < depth) {
                values.add(treeNode.val);
            }
            if (treeNode.right != null) {
                queue.add(new Node(treeNode.right, depth+1));
            }
            if (treeNode.left != null) {
                queue.add(new Node(treeNode.left, depth+1));
            }
        }

        return values;
    }
}

class Node {

    TreeNode treeNode;
    int depth;

    Node(TreeNode treeNode, int depth) {
        this.treeNode = treeNode;
        this.depth = depth;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
