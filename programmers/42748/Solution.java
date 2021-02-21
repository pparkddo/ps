import java.util.stream.IntStream;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int t = 0; t < commands.length; t++) {
            int[] sliced = IntStream
                .range(commands[t][0]-1, commands[t][1])
                .map(i -> array[i])
                .sorted()
                .toArray();
            answer[t] = sliced[commands[t][2]-1];
        }
        return answer;
    }
}
