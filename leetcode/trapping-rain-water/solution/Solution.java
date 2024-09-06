import java.util.Stack;

class Solution {

    public int trap(int[] height) {
        int answer = 0;

        Stack<Integer> stack = new Stack<>();

        int i = 0;
        while (i < height.length) {
            int currentHeight = height[i];
            if (stack.isEmpty() || height[stack.peek()] >= currentHeight) {
                stack.push(i++);
                continue;
            }

            int indexThatWillBeCalculated = stack.pop();
            if (!stack.isEmpty()) {
                int minHeight = Math.min(height[stack.peek()], currentHeight);
                int calculatedArea = (minHeight - height[indexThatWillBeCalculated]) * (i - stack.peek() - 1);
                answer += calculatedArea;
            }
        }

        return answer;
    }
}
