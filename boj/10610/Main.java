import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.stream.Collectors;

public class Main {

    private static String solution(String n) {
        String sorted = n.chars()
                         .map(each -> each - '0')
                         .boxed()
                         .sorted(Collections.reverseOrder())
                         .map(String::valueOf)
                         .collect(Collectors.joining());
        if (sorted.charAt(sorted.length()-1) != '0') {
            return "-1";
        }
        if (sorted.chars().map(each -> each - '0').sum() % 3 != 0) {
            return "-1";
        }
        return sorted;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String n = in.readLine();
        in.close();
        System.out.println(solution(n));
    }
}
