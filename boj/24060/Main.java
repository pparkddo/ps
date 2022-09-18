import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private final int[] tmp;
    private final int k;
    private int answer = -1;
    private int count = 0;

    public Main(int n, int k) {
        this.k = k;
        this.tmp = new int[n];
    }

    private void merge_sort(int[] numbers, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            merge_sort(numbers, p, q);
            merge_sort(numbers, q + 1, r);
            merge(numbers, p, q, r);
        }
    }

    private void merge(int[] numbers, int p, int q, int r) {
        int i = p, j = q + 1, t = 0;

        while (i <= q && j <= r) {
            if (numbers[i] <= numbers[j]) {
                tmp[t++] = numbers[i++];
            } else {
                tmp[t++] = numbers[j++];
            }
        }

        while (i <= q) {
            tmp[t++] = numbers[i++];
        }

        while (j <= r) {
            tmp[t++] = numbers[j++];
        }

        i = p;
        t = 0;

        while (i <= r) {
            int storedValue = tmp[t++];
            numbers[i++] = storedValue;

            this.count++;
            if (this.count == this.k) {
                this.answer = storedValue;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");

        int n = Integer.parseInt(nm[0]);
        int k = Integer.parseInt(nm[1]);

        int[] numbers = Arrays.stream(in.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        Main main = new Main(n, k);
        main.merge_sort(numbers, 0, numbers.length - 1);

        System.out.println(main.answer);
    }
}
