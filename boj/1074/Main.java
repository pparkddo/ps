import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int sequence = 0;
    private static int answer = -1;

    private static boolean shouldTraverse(int row, int column, int size, int r, int c) {
        return (row <= r && r <= row + size) && (column <= c && c <= column + size);
    }

    private static void traverse(int row, int column, int size, int r, int c) {
        if (!shouldTraverse(row, column, size, r, c)) {
            sequence += size*size;
            return;
        }
        if (size == 1) {
            sequence++;
            if (row == r && column == c) {
                answer += sequence;
            }
            return;
        }
        int nextSize = size / 2;
        traverse(row, column, nextSize, r, c);
        traverse(row, column+nextSize, nextSize, r, c);
        traverse(row+nextSize, column, nextSize, r, c);
        traverse(row+nextSize, column+nextSize, nextSize, r, c);
    }

    private static int solution(int n, int r, int c) {
        traverse(0, 0, (int) Math.pow(2, n), r, c);
        return answer;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nrc = in.readLine().split(" ");
        int n = Integer.parseInt(nrc[0]);
        int r = Integer.parseInt(nrc[1]);
        int c = Integer.parseInt(nrc[2]);
        in.close();
        System.out.println(solution(n, r, c));
    }
}
