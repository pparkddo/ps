import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] ab = in.readLine().split(" ");
        in.close();

        int a = Integer.parseInt(ab[0]);
        int b = Integer.parseInt(ab[1]);
        int answer = 0;
        int index = 0;
        int number = 1;
        int repeat = number;
        while (index < b) {
            if (repeat == 0) {
                number++;
                repeat = number;
                continue;
            }
            if (a <= index+1 && index+1 <= b) {
                answer += number;
            }
            repeat--;
            index++;
        }
        System.out.println(answer);
    }
}
