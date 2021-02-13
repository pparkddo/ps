import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static int[][] board = new int[9][9];
    private static int[] missingRows;
    private static int[] missingColumns;
    private static int missingCount;
    private static final int ONE = 1;
    private static final int NINE = 9;
    private static final int MISSING = 0;
    private static boolean isEnd = false;

    private static void print_board() {
        StringBuilder sb = new StringBuilder(); 
        for (int i = 0; i < NINE; i++) {
            for (int j = 0; j < NINE; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString().stripTrailing());
    }
    
    private static void dfs(int index) {
        if (isEnd) {
            return;
        }
        if (index == missingCount) {
            print_board();
            isEnd = true;
            return;
        }
        int missingRow = missingRows[index];
        int missingColumn = missingColumns[index];
        for (int number = ONE; number <= NINE; number++) {
            if (!isValidNumber(missingRow, missingColumn, number)) {
                continue;
            }
            board[missingRow][missingColumn] = number;
            dfs(index+1);
            board[missingRow][missingColumn] = MISSING;
        }
    }

    private static boolean isValidNumber(int row, int column, int number) {
        for (int i = 0; i < NINE; i++) {
            if (number == board[row][i]) {
                return false;
            }
        }
        for (int i = 0; i < NINE; i++) {
            if (number == board[i][column]) {
                return false;
            }
        }
        int initialRow = row / 3 * 3;
        int initialColumn = column / 3 * 3;
        for (int i = initialRow; i < initialRow + 3; i++) {
            for (int j = initialColumn; j < initialColumn + 3; j++) {
                if (number == board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> missingRowList = new ArrayList<>();
        List<Integer> missingColumnList = new ArrayList<>();
        for (int i = 0; i < NINE; i++) {
            String[] values = in.readLine().split(" ");
            for (int j = 0; j < NINE; j++) {
                int value = Integer.parseInt(values[j]);
                if (value == MISSING) {
                    missingRowList.add(i);
                    missingColumnList.add(j);
                }
                board[i][j] = value;
            }
        }
        in.close();

        missingRows = missingRowList.stream().mapToInt(Integer::valueOf).toArray();
        missingColumns = missingColumnList.stream().mapToInt(Integer::valueOf).toArray();
        missingCount = missingRows.length;

        dfs(0);
    }
}
