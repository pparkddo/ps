import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static long solution(int n, int[][] abcd) {
        int[] abSums = new int[n*n];
        int[] cdSums = new int[n*n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                abSums[index] = abcd[0][i]+abcd[1][j];
                cdSums[index] = abcd[2][i]+abcd[3][j];
                index++;
            }
        }
        Arrays.sort(abSums);
        Arrays.sort(cdSums);
        return getCount(n, abSums, cdSums);
    }

    private static long getCount(int n, int[] leftNumbers, int[] rightNumbers) {
        long count = 0;

        int left = 0;
        int right = rightNumbers.length - 1;
        while (left < n*n && right >= 0) {
            int leftNumber = leftNumbers[left];
            int rightNumber = rightNumbers[right];
            if (leftNumber + rightNumber == 0) {
                long countOfSameLeftNumber = 0;
                while (left < n*n && leftNumbers[left] == leftNumber) {
                    countOfSameLeftNumber++;
                    left++;
                }
                long countOfSameRightNumber = 0;
                while (right >= 0 && rightNumbers[right] == rightNumber) {
                    countOfSameRightNumber++;
                    right--;
                }
                count += countOfSameLeftNumber * countOfSameRightNumber;
            }
            else if (leftNumber + rightNumber < 0) {
                left++;
            }
            else if (leftNumber + rightNumber > 0) {
                right--;
            }
        }

        return count;
    }

    private static int upperBound(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (target >= numbers[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    
    private static int lowerBound(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (target <= numbers[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[][] abcd = new int[4][n];
        for (int index = 0; index < n; index++) {
            String[] each = in.readLine().split(" ");
            for (int i = 0; i < each.length; i++) {
                abcd[i][index] = Integer.parseInt(each[i]);
            }
        }
        in.close();
        System.out.println(solution(n, abcd));
    }
}
