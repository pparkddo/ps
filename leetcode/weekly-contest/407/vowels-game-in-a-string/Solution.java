import java.util.Set;

class Solution {

    public boolean doesAliceWin(String s) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');

        for (char c : s.toCharArray()) {
            if (vowels.contains(c)) {
                return true;
            }
        }

        return false;
    }
}