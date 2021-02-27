import java.util.Stack;

class Solution {

    private String makeCorrect(String s) {
        if (s.isEmpty()) {
            return s;
        }
        String u = getBalanced(s);
        String v = getV(s, u);
        if (isCorrect(u)) {
            return u + makeCorrect(v);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(makeCorrect(v));
        sb.append(")");
        sb.append(reverse(u.substring(1, u.length()-1)));
        return sb.toString();
    }

    private String reverse(String s) {
        StringBuilder reversed = new StringBuilder();
        for (char c : s.toCharArray()) {
            char reverse = c == '(' ? ')' : '(';
            reversed.append(reverse);
        }
        return reversed.toString();
    }

    private String getV(String s, String u) {
        return s.substring(u.length());
    }
    
    private String getBalanced(String s) {
        StringBuilder balanced = new StringBuilder();
        int index = 0;
        int count = s.charAt(index) == '(' ? 1 : -1;
        balanced.append(s.charAt(index));
        index++;
        while (count != 0) {
            assert index < s.length();
            char c = s.charAt(index);
            if (c == '(') {
                count++;
            }
            else {
                count--;
            }
            balanced.append(c);
            index++;
        }
        return balanced.toString();
    }

    private boolean isCorrect(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character each : s.toCharArray()) {
            if (each == '(') {
                stack.push(each);
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }
            if (stack.peek() == ')') {
                return false;
            }
        }
        return true;
    }

    public String solution(String p) {
        return makeCorrect(p);
    }
}
