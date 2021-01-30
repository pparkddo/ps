import java.util.Arrays;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] numbers = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();

        int answer = 0;
        for (int number : numbers) {
            answer = answer + (int) Math.pow(number, 2);
        }
        answer = answer % 10;
        System.out.println(answer);
    }
}