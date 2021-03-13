public class Solution {
    
    private static final int SAFE = 0;

    static int jumpingOnClouds(int[] c) {
        int answer = 0;
        int index = 0;
        while (true) {
            if (index == c.length-1) {
                break;                
            }
            int step = index+2 < c.length && c[index+2] == SAFE ? 2 : 1;
            index += step;
            answer++;
        }
        return answer;
    }
}
