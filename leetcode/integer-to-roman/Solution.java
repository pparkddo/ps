import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

class Solution {

    private static final Map<Integer, Character> SYMBOLS = Map.of(
        1, 'I',
        5, 'V',
        10, 'X',
        50, 'L',
        100, 'C',
        500, 'D',
        1000, 'M'
    );

    public String intToRoman(int num) {
        Deque<Character> temp = new LinkedList<>();

        int multiplier = 1;
        while (num > 0) {
            int digit = num % 10;

            if (digit == 4) {
                temp.addFirst(SYMBOLS.get(5 * multiplier));
                temp.addFirst(SYMBOLS.get(multiplier));
            } else if (digit == 9) {
                temp.addFirst(SYMBOLS.get(10 * multiplier));
                temp.addFirst(SYMBOLS.get(multiplier));
            } else if (digit == 5) {
                temp.addFirst(SYMBOLS.get(digit * multiplier));
            } else if (digit < 5) {
                for (int i = 0; i < digit; i++) {
                    temp.addFirst(SYMBOLS.get(multiplier));
                }
            } else {
                for (int i = 0; i < digit - 5; i++) {
                    temp.addFirst(SYMBOLS.get(multiplier));
                }
                temp.addFirst(SYMBOLS.get(5 * multiplier));
            }

            num /= 10;
            multiplier *= 10;
        }

        StringBuilder sb = new StringBuilder();
        while (!temp.isEmpty()) {
            sb.append(temp.pollFirst());
        }
        return sb.toString();
    }
}
