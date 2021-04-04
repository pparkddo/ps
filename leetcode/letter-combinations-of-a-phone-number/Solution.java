import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    
    String digits;
    String[] combination;
    Map<Integer, List<String>> letters = new HashMap<>();
    List<String> answer = new ArrayList<>();

    private void initializeLetters() {
        letters.put(2, List.of("a", "b", "c"));
        letters.put(3, List.of("d", "e", "f"));
        letters.put(4, List.of("g", "h", "i"));
        letters.put(5, List.of("j", "k", "l"));
        letters.put(6, List.of("m", "n", "o"));
        letters.put(7, List.of("p", "q", "r", "s"));
        letters.put(8, List.of("t", "u", "v"));
        letters.put(9, List.of("w", "x", "y", "z"));
    }

    private int getDigit(String digits, int index) {
        return Integer.parseInt(String.valueOf(digits.charAt(index)));
    }

    private void dfs(int depth) {
        if (depth == digits.length()) {
            StringBuilder c = new StringBuilder();
            for (String each : combination) {
                c.append(each);
            }
            answer.add(c.toString());
            return;
        }
        for (String letter : letters.get(getDigit(digits, depth))) {
            combination[depth] = letter;
            dfs(depth+1);
        }
    }
    
    public List<String> letterCombinations(String digits) {
        this.digits = digits;
        this.combination = new String[digits.length()];
        initializeLetters();

        if (digits.isBlank()) {
            return answer;
        }

        dfs(0);
        return answer;
    }
}
