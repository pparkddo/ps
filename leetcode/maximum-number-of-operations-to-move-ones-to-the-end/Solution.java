class Solution {

    public int maxOperations(String s) {
        int answer = 0;
        int numberOfOnes = 0;

        if (s.charAt(0) == '1') {
            numberOfOnes++;
        }

        for (int i = 1; i < s.length(); i++) {
            if (
                (s.charAt(i - 1) == '0' && s.charAt(i) == '1')
                    || (i == s.length() - 1 && s.charAt(i) == '0')
            ) {
                answer += numberOfOnes;
                numberOfOnes++;
                continue;
            }
            if (s.charAt(i) == '1') {
                numberOfOnes++;
            }
        }

        return answer;
    }
}
