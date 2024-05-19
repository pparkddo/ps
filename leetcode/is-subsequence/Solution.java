class Solution {
    public boolean isSubsequence(String s, String t) {
        int index = 0;
        if (s.isEmpty()) {
            return true;
        }
        for (char c : t.toCharArray()) {
            if (s.charAt(index) == c) {
                index++;
            }
            if (index == s.length()) {
                return true;
            }
        }
        return false;
    }
}