import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final int STUDENT_COUNT = 5;

    private static int solution(int[] scores) {
        int minimumScore = 40;
        return Arrays.stream(scores).map(each -> Math.max(each, minimumScore)).sum() / scores.length;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] scores = new int[STUDENT_COUNT];
        for (int index = 0; index < STUDENT_COUNT; index++) {
            scores[index] = Integer.parseInt(in.readLine());
        }
        in.close();
        System.out.println(solution(scores));
    }
}
