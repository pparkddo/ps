import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();

        List<Integer> answers = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            while (n % i  == 0) {
                n = n / i;
                answers.add(i);
            }
        }
        System.out.println(answers.stream().map(i->i.toString()).collect(Collectors.joining("\n")));
    }
}