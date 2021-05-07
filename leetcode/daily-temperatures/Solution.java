import java.util.Stack;

class Solution {

    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[T.length];
        for (int index = 0; index < T.length; index++) {
            while (!stack.isEmpty() && T[index] > T[stack.peek()]) {
                int i = stack.pop();
                answer[i] = index - i;
            }
            stack.push(index);
        }
        return answer;
    }
}
