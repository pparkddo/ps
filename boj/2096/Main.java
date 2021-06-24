import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static String solution(int n, int[][] numbers) {
        return getMaximumScore(numbers) + " " + getMinimumScore(numbers);
    }

    private static int getMaximumScore(int[][] numbers) {
        int[][] maximum = new int[numbers.length][numbers[0].length];
        for (int rowIndex = 0; rowIndex < numbers.length; rowIndex++) {
            if (rowIndex == 0) {  // base case
                for (int columnIndex = 0; columnIndex < maximum[0].length; columnIndex++) {
                    maximum[rowIndex][columnIndex] = numbers[rowIndex][columnIndex];
                }
                continue;
            }
            int previousRowIndex = rowIndex - 1;
            int first = Math.max(maximum[previousRowIndex][0], maximum[previousRowIndex][1]);
            int second = Math.max(first, maximum[previousRowIndex][2]);
            int third = Math.max(maximum[previousRowIndex][1], maximum[previousRowIndex][2]);
            maximum[rowIndex][0] = first += numbers[rowIndex][0];
            maximum[rowIndex][1] = second += numbers[rowIndex][1];
            maximum[rowIndex][2] = third += numbers[rowIndex][2];
        }
        int lastRowIndex = numbers.length - 1;
        return Math.max(Math.max(maximum[lastRowIndex][0], maximum[lastRowIndex][1]), maximum[lastRowIndex][2]);
    }

    private static int getMinimumScore(int[][] numbers) {
        int[][] minimum = new int[numbers.length][numbers[0].length];
        for (int rowIndex = 0; rowIndex < numbers.length; rowIndex++) {
            if (rowIndex == 0) {  // base case
                for (int columnIndex = 0; columnIndex < minimum[0].length; columnIndex++) {
                    minimum[rowIndex][columnIndex] = numbers[rowIndex][columnIndex];
                }
                continue;
            }
            int previousRowIndex = rowIndex - 1;
            int first = Math.min(minimum[previousRowIndex][0], minimum[previousRowIndex][1]);
            int second = Math.min(first, minimum[previousRowIndex][2]);
            int third = Math.min(minimum[previousRowIndex][1], minimum[previousRowIndex][2]);
            minimum[rowIndex][0] = first += numbers[rowIndex][0];
            minimum[rowIndex][1] = second += numbers[rowIndex][1];
            minimum[rowIndex][2] = third += numbers[rowIndex][2];
        }
        int lastRowIndex = numbers.length - 1;
        return Math.min(Math.min(minimum[lastRowIndex][0], minimum[lastRowIndex][1]), minimum[lastRowIndex][2]);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[][] numbers = new int[n][3];
        for (int index = 0; index < n; index++) {
            int[] row = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            numbers[index] = row;
        }
        in.close();
        System.out.println(solution(n, numbers));
    }
}
