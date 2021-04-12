import java.util.ArrayList;
import java.util.List;

class Solution {

    List<String> answer = new ArrayList<>();

    private void dfs(int n, int depth, int openCount, StringBuilder s) {
        if (depth == 2 * n) {
            answer.add(s.toString());
            return;
        }
        if (openCount < n) {
            dfs(n, depth+1, openCount+1, s.append("("));
            s.deleteCharAt(s.length()-1);
        }
        if (depth < 2 * openCount) {  // closeCount(=depth - openCount) < openCount
            dfs(n, depth+1, openCount, s.append(")"));
            s.deleteCharAt(s.length()-1);
        }
    }

    public List<String> generateParenthesis(int n) {
        dfs(n, 0, 0, new StringBuilder());
        return answer;
    }
}
