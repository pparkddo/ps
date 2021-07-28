import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int[][] dp;
    private static int NOT_VISITED = -1;

    private static int solution(String s) {
        dp = new int[s.length()+1][s.length()+1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], NOT_VISITED);
        }
        return getGeneLength(0, s.length()-1, s);
    }

    private static int getGeneLength(int leftIndex, int rightIndex, String s) {
        if (leftIndex >= rightIndex) {
            return 0;
        }
        if (dp[leftIndex][rightIndex] != NOT_VISITED) {
            return dp[leftIndex][rightIndex];
        }
        int length = 0;
        if (isGene(leftIndex, rightIndex, s)) {
            length = 2 + getGeneLength(leftIndex+1, rightIndex-1, s);
        }
        for (int index = leftIndex; index < rightIndex; index++) {
            length = Math.max(length, getGeneLength(leftIndex, index, s)+getGeneLength(index+1, rightIndex, s));
        }
        return dp[leftIndex][rightIndex] = length;
    }

    private static boolean isGene(int leftIndex, int rightIndex, String s) {
        char left = s.charAt(leftIndex);
        char right = s.charAt(rightIndex);
        if (left == 'a' && right == 't') {
            return true;
        }
        if (left == 'g' && right == 'c') {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        in.close();
        System.out.println(solution(s));
    }
}
