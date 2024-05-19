class Solution {

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int['z' - 'a' + 1];

        for (char c : magazine.toCharArray()) {
            count[c - 'a']++;
        }

        for (char c : ransomNote.toCharArray()) {
            int cnt = --count[c - 'a'];
            if (cnt < 0) {
                return false;
            }
        }

        return true;
    }
}
