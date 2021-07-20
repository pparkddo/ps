import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int solution(String word) {
        return isPalindrome(0, word.length()-1, 0, word);
    }

    private static int isPalindrome(int left, int right, int skipCount, String word) {
        if (left > right) {
            return skipCount;
        }
        if (skipCount >= 2) {
            return skipCount;
        }
        if (word.charAt(left) != word.charAt(right)) {
            return Math.min(
                isPalindrome(left+1, right, skipCount+1, word),
                isPalindrome(left, right-1, skipCount+1, word));
        }
        return isPalindrome(left+1, right-1, skipCount, word);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder answers = new StringBuilder();
        for (int i = 0; i < t; i++) {
            answers.append(solution(in.readLine())).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}
