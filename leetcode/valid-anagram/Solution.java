class Solution {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        return isValid(s, t);
    }

    private boolean isValid(String s, String t) {
        int[] container = new int[26];
        int length = s.length();
        for (int index = 0; index < length; index++) {
            container[charToIndex(s.charAt(index))]++;
            container[charToIndex(t.charAt(index))]--;
        }
        for (int each : container) {
            if (each != 0) {
                return false;
            }
        }
        return true;
    }

    private int charToIndex(char c) {
        return c - 'a';
    }
}