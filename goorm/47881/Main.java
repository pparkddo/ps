import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int getCount(int length, int k) {
        int count = (length-1) / (k-1);
        if (getRemainder(length, k) == 0) {
            return count;
        }
        return count + 1;
    }

    private static int getRemainder(int length, int k) {
        return (length-1) % (k-1);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = in.readLine().split(" ");
        int k = Integer.parseInt(nk[1]);
        int[] numbers = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        in.close();

        int count = getCount(numbers.length, k);

        System.out.println(count);
    }
}
