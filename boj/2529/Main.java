import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    private static String[] solution(int k, String[] signs) {
        int[] maximumNumbers = new int[k+1];
        for (int number = 9; number >= 0; number--) {
            boolean[] visited = new boolean[10];
            visited[number] = true;
            maximumNumbers[0] = number;
            int[] result = getMaximumNumber(number, 1, maximumNumbers, signs, visited);
            if (result != null) {
                break;
            }
            visited[number] = false;
        }

        int[] minimumNumbers = new int[k+1];
        for (int number = 0; number < 10; number++) {
            boolean[] visited = new boolean[10];
            visited[number] = true;
            minimumNumbers[0] = number;
            int[] result = getMinimumNumbers(number, 1, minimumNumbers, signs, visited);
            if (result != null) {
                break;
            }
            visited[number] = false;
        }
        
        return new String[] {convertToString(maximumNumbers), convertToString(minimumNumbers)};
    }

    private static String convertToString(int[] numbers) {
        return Arrays.stream(numbers).mapToObj(String::valueOf).collect(Collectors.joining());
    }

    private static int[] getMaximumNumber(int number, int index, int[] numbers, String[] signs, boolean[] visited) {
        if (index == numbers.length) {
            return numbers;
        }

        String sign = signs[index-1];

        if (isGreater(sign)) {
            for (int n = 9; n > number; n--) {
                if (visited[n]) {
                    continue;
                }
                visited[n] = true;
                numbers[index] = n;
                int[] result = getMaximumNumber(n, index+1, numbers, signs, visited);
                if (result != null) {
                    return result;
                } 
                visited[n] = false;
            }
        }

        else if (isLess(sign)) {
            for (int n = number-1; n >= 0; n--) {
                if (visited[n]) {
                    continue;
                }
                visited[n] = true;
                numbers[index] = n;
                int[] result = getMaximumNumber(n, index+1, numbers, signs, visited);
                if (result != null) {
                    return result;
                } 
                visited[n] = false;
            }
        }

        return null;
    }

    private static int[] getMinimumNumbers(int number, int index, int[] numbers, String[] signs, boolean[] visited) {
        if (index == numbers.length) {
            return numbers;
        }

        String sign = signs[index-1];

        if (isGreater(sign)) {
            for (int n = number+1; n <= 9; n++) {
                if (visited[n]) {
                    continue;
                }
                visited[n] = true;
                numbers[index] = n;
                int[] result = getMinimumNumbers(n, index+1, numbers, signs, visited);
                if (result != null) {
                    return result;
                } 
                visited[n] = false;
            }
        }

        else if (isLess(sign)) {
            for (int n = 0; n < number; n++) {
                if (visited[n]) {
                    continue;
                }
                visited[n] = true;
                numbers[index] = n;
                int[] result = getMinimumNumbers(n, index+1, numbers, signs, visited);
                if (result != null) {
                    return result;
                } 
                visited[n] = false;
            }
        }

        return null;
    }

    private static boolean isGreater(String sign) {
        return "<".equals(sign);
    }

    private static boolean isLess(String sign) {
        return ">".equals(sign);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(in.readLine());
        String[] signs = in.readLine().split(" ");
        in.close();
        System.out.println(convertToAnswerFormat(solution(k, signs)));
    }

    private static String convertToAnswerFormat(String[] result) {
        return new StringBuilder()
            .append(result[0])
            .append("\n")
            .append(result[1])
            .toString();
    }
}
