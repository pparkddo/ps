import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

class Solution {

    private static final int FRIEND_COUNT = 8;
    private static final Map<Character, Integer> friendIndices
        = Map.of('A', 0, 'C', 1, 'F', 2, 'J', 3, 'M', 4, 'N', 5, 'R', 6, 'T', 7);
    private static final int UNKNOWN = -1;

    public int solution(int n, String[] data) {
        List<Expression> expressions = new ArrayList<>();
        for (String d : data) {
            expressions.add(parseExpression(d));
        }
        int[] positions = new int[FRIEND_COUNT];
        Arrays.fill(positions, UNKNOWN);
        return dfs(0, 0, positions, new boolean[FRIEND_COUNT], expressions);
    }

    private int dfs(int friendIndex, int position, int[] positions,
                    boolean[] visited, List<Expression> expressions) {
        if (position == FRIEND_COUNT) {
            return 1;
        }
        int count = 0;
        for (int each = 0; each < FRIEND_COUNT; each++) {
            if (visited[each]) {
                continue;
            }
            positions[each] = position;
            visited[each] = true;
            if (!isExpressionsMatch(positions, expressions)) {
                positions[each] = UNKNOWN;
                visited[each] = false;
                continue;
            }
            count += dfs(each, position+1, positions, visited, expressions);
            positions[each] = UNKNOWN;
            visited[each] = false;
        }
        return count;
    }

    private boolean isExpressionsMatch(int[] positions, List<Expression> expressions) {
        for (Expression expression : expressions) {
            if (!isSet(expression.a, expression.b, positions)) {
                continue;
            }
            if (!expression.match(getGap(expression.a, expression.b, positions))) {
                return false;
            }
        }
        return true;
    }

    private boolean isSet(int a, int b, int[] positions) {
        return positions[a] != UNKNOWN && positions[b] != UNKNOWN;
    }

    private int getGap(int a, int b, int[] positions) {
        return Math.abs(positions[a] - positions[b]) - 1;
    }

    private Expression parseExpression(String d) {
        int a = charToFriendIndex(d.charAt(0));
        int b = charToFriendIndex(d.charAt(2));
        char sign = d.charAt(3);
        int gap = d.charAt(4) - '0';
        return new Expression(a , b, sign, gap);
    }

    private int charToFriendIndex(char c) {
        return friendIndices.get(c);
    }
}

class Expression {

    int a;
    int b;
    char sign;
    int gap;

    Expression(int a, int b, char sign, int gap) {
        this.a = a;
        this.b = b;
        this.sign = sign;
        this.gap = gap;
    }

    public boolean match(int g) {
        if ('=' == sign) {
            return gap == g;
        }
        if ('<' == sign) {
            return g < gap;
        }
        if ('>' == sign) {
            return g > gap;
        }
        throw new IllegalArgumentException("Unknown sign");
    }

    @Override
    public String toString() {
        return a + " " + b + " " + sign + " " + gap;
    }
}
