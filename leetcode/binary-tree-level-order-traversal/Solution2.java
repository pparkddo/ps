import java.util.ArrayList;
import java.util.List;

class Solution {

    private List<List<Integer>> answer = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        traverse(root, 0);
        return answer;
    }

    private void traverse(TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        if (answer.size() <= depth) {
            answer.add(new ArrayList<>());
        }
        answer.get(depth).add(node.val);
        traverse(node.left, depth+1);
        traverse(node.right, depth+1);
    }
}