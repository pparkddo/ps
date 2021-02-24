// https://programmers.co.kr/learn/courses/30/lessons/68644
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] numbers) {
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i+1; j < numbers.length; j++) {
                answer.add(numbers[i]+numbers[j]);
            }
        }
        return answer.stream().distinct().sorted().mapToInt(i -> i).toArray();
    }
}
