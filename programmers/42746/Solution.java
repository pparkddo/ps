import java.util.Collections;
import java.util.Arrays;

class Number implements Comparable<Number> {
    
    int value;
    
    Number(int value) {
        this.value = value;
    }
    
    @Override
    public int compareTo(Number number) {
        if (this.length() == number.length()) {
            return Integer.compare(this.value, number.value);
        }
        return Integer.compare(this.repeatUntil(4), number.repeatUntil(4));
    }
    
    private int length() {
        if (this.value == 0) {
            return 1;
        }
        return (int) Math.log10(this.value) + 1;
    }
    
    private int repeatUntil(int n) {
        StringBuilder s = new StringBuilder();
        while (s.length() <= n) {
            s.append(this.value);
        }
        return Integer.parseInt(s.toString().substring(0, n));
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}

class Solution {
    public String solution(int[] numbers) {
        Number[] values = new Number[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            values[i] = new Number(numbers[i]);
        }
        Arrays.sort(values, Collections.reverseOrder());
        if (values[0].value == 0) {
            return "0";
        }
        StringBuilder answer = new StringBuilder();
        for (Number each : values) {
            answer.append(each);
        }
        return answer.toString();
    }
}

class Main {

    public static void main(String[] args) {
        new Solution().solution(new int[] {1, 2, 3, 0, 113, 12});
        new Solution().solution(new int[] {0, 0, 0, 0});
        new Solution().solution(new int[] {21, 121});
        new Solution().solution(new int[] {113, 12});
    }
}