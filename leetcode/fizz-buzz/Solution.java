import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<String> fizzBuzz(int n) {
        List<String> answer = new ArrayList<>();
        for (int index = 1; index <= n; index++) {
            boolean isFizz = index % 3 == 0;
            boolean isBuzz = index % 5 == 0;
            if (isFizz && isBuzz) answer.add("FizzBuzz");
            else if (isFizz) answer.add("Fizz");
            else if (isBuzz) answer.add("Buzz");
            else answer.add(String.valueOf(index));
        }
        return answer;
    }
}