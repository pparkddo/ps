import java.util.Scanner;

public class Main {

    private static boolean isEndNumber(int number) {
        int restSixCount = 3;
        while (number != 0) {
            if (restSixCount == 0) {
                return true;
            }
            boolean isSix = number % 10 == 6;
            if (isSix) {
                restSixCount -= 1;
            }
            if (restSixCount > 0 && restSixCount < 3 && !isSix) {
                restSixCount = 3;
            }
            number = number / 10;
        }
        return restSixCount == 0;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int count = 0;
        int number = 666;

        while (true) {
            if (isEndNumber(number)) {
                count += 1;
            }
            if (count == n) {
                break;
            }
            number += 1;
        }

        System.out.println(number);

        in.close();
    }
}
