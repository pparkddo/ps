class Solution {
    
    private String convertBase(int number, int base) {
        return Integer.toString(number, base);
    }
    
    public String solution(int n, int t, int m, int p) {
        StringBuilder values = new StringBuilder();
        int number = 0;
        while (values.length() / m <= t) {
            values.append(convertBase(number++, n));
        }
        
        StringBuilder answer = new StringBuilder();
        int turn = 1;
        p = p == m ? 0 : p;
        for (char value : values.toString().toCharArray()) {
            if (answer.length() == t) {
                break;
            }
            if (turn++ % m == p) {
                answer.append(value);
            }
        }
        
        return answer.toString().toUpperCase();
    }
}
