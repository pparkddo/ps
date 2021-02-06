import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = in.readLine().strip();
        }
        in.close();

        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length()) {
                    return 1;
                }
                if (o1.length() < o2.length()) {
                    return -1;
                }
                return o1.compareTo(o2);
            }
        });

        StringBuilder answer = new StringBuilder();
        String previous = null;
        for (String word: words) {
            if (previous != null && previous.equals(word)) {
                continue;
            }
            answer.append(word).append("\n");
            previous = word;
        }
        System.out.println(answer.toString().stripTrailing());
    }
}
