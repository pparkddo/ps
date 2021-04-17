import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static StringBuilder answers = new StringBuilder();
    private static char[] password;

    private static boolean isVowel(char c) {
        return (
            c == 'a'
            || c == 'e'
            || c == 'i'
            || c == 'o'
            || c == 'u'
        );
    }

    private static void dfs(int startIndex, int depth, int vowelCount, int l, char[] words) {
        if (depth == l) {
            if (vowelCount >= 1 && depth-vowelCount >= 2) {
                answers.append(new String(password)).append("\n");
            }
            return;
        }
        for (int index = startIndex; index < words.length; index++) {
            char word = words[index];
            password[depth] = word;
            dfs(index+1, depth+1, isVowel(word) ? vowelCount+1 : vowelCount, l, words);
        }
    }

    private static String solution(int l, int c, char[] words) {
        password = new char[l];
        Arrays.sort(words);
        dfs(0, 0, 0, l, words);
        return answers.toString().trim();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] lc = in.readLine().split(" ");
        int l = Integer.parseInt(lc[0]);
        int c = Integer.parseInt(lc[1]);
        char[] words = new char[c];
        String[] input = in.readLine().split(" ");
        for (int index = 0; index < c; index++) {
            words[index] = input[index].charAt(0);
        }
        in.close();
        System.out.println(solution(l, c, words));
    }
}
