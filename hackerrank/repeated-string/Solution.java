public class Solution {
    
    private static long getACount(String s) {
        return s.chars().filter(each -> each == 'a').count();
    }

    static long repeatedString(String s, long n) {
        long aCount = getACount(s);
        long answer = n / s.length() * aCount;
        long remainder = n % s.length();
        if (remainder == 0) {
            return answer;            
        }
        return answer + getACount(s.substring(0, (int)remainder));
    }
}
