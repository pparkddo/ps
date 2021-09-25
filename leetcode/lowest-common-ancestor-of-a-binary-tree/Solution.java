import java.util.HashSet;
import java.util.Set;

class Solution {

    private Set<Integer> set = new HashSet<>();
    private TreeNode answer = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        traverseP(root, p);
        traverseQ(root, q);
        return answer;
    }

    private boolean traverseP(TreeNode current, TreeNode p) {
        if (current == p) {
            set.add(current.val);
            return true;
        }
        if (current == null) {
            return false;
        }
        if (traverseP(current.left, p) || traverseP(current.right, p)) {
            set.add(current.val);
            return true;
        }
        return false;
    }

    private boolean traverseQ(TreeNode current, TreeNode q) {
        if (current == q) {
            if (set.contains(current.val)) {
                answer = current;
            }
            return true;
        }
        if (current == null) {
            return false;
        }
        if (traverseQ(current.left, q) || traverseQ(current.right, q)) {
            if (answer == null && set.contains(current.val)) {
                answer = current;
            }
            return true;
        }
        return false;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
