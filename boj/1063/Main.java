import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static String solution(Position king, Position stone, List<String> movements) {
        for (String movement : movements) {
            boolean isKingMoveSucceed= king.move(movement);
            if (!isKingMoveSucceed) {
                king.rollback();
                continue;
            }
            if (king.equals(stone)) {
                boolean isStoneMoveSucceed = stone.move(movement);
                if (!isStoneMoveSucceed) {
                    king.rollback();
                    stone.rollback();
                }
            }
        }
        return new StringBuilder().append(king).append("\n").append(stone).toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] kingStoneN = in.readLine().split(" ");
        Position king = new Position(kingStoneN[0]);
        Position stone = new Position(kingStoneN[1]);
        int n = Integer.parseInt(kingStoneN[2]);
        List<String> movements = new ArrayList<>();
        for (int index = 0; index < n; index++) {
            movements.add(in.readLine());
        }
        in.close();
        System.out.println(solution(king, stone, movements));
    }
}

class Position {

    int previousRow;
    int previousColumn;
    int row;
    int column;

    Position(String s) {
        column = s.charAt(0) - 'A';
        row = s.charAt(1) - '1';
    }

    public boolean move(String movement) {
        previousRow = row;
        previousColumn = column;
        for (char m : movement.toCharArray()) {
            move(m);
        }
        return isValid();
    }

    private void move(char m) {
        switch (m) {
            case 'R':
                column += 1;
                break;
            case 'L':
                column -= 1;
                break;
            case 'B':
                row -= 1;
                break;
            case 'T':
                row += 1;
                break;
        }
    }

    public void rollback() {
        row = previousRow;
        column = previousColumn;
    }

    private boolean isValid() {
        return row >= 0 && column >= 0 && row < 8 && column < 8;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append((char)(column+'A')).append((char)(row+'1')).toString();
    }

    @Override
    public boolean equals(Object obj) {
        Position p = (Position) obj;
        return row == p.row && column == p.column;
    }
}
