import java.util.HashSet;
import java.util.Set;

class Solution {

    public int[] solution(int n, String[] words) {
        Set<String> set = new HashSet<>();
        for (int index = 0; index < words.length; index++) {
            String word = words[index];
            if (index == 0) {
                set.add(word);
                continue;
            }
            if(!isCorrectContinuousWord(words[index-1], word) || !isUnique(word, set)) {
                int sequence = (index % n) + 1; 
                int count = (index / n) + 1;
                return new int[] {sequence, count};
            }
            set.add(word);
        }
        return new int[] {0, 0};
    }

    private boolean isCorrectContinuousWord(String previous, String next) {
        return previous.charAt(previous.length()-1) == next.charAt(0);
    }

    private boolean isUnique(String word, Set<String> set) {
        return !set.contains(word);
    }
}