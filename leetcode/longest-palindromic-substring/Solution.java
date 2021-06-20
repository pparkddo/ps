class Solution {

    public String longestPalindrome(String s) {
        int length = s.length();
        int maxStart = 0;
        int maxEnd = 0;
        boolean[][] dp = new boolean[length][length];
        for (int start = length - 1; start >= 0; start--) {
            for (int end = start; end < length; end++) {
                if (s.charAt(start) != s.charAt(end)) {
                    continue;
                }
                dp[start][end] = (end - start < 2 || dp[start+1][end-1]);
                if (dp[start][end] && end - start > maxEnd - maxStart) {
                    maxStart = start;
                    maxEnd = end;
                }
            }
        }
        return s.substring(maxStart, maxEnd+1);
    }
}
