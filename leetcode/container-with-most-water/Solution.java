class Solution {

    private int getArea(int[] height, int left, int right) {
        int leftHeight = height[left];
        int rightHeight = height[right];
        return Math.min(leftHeight, rightHeight) * (right - left);
    }

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int answer = 0;
        while (left <= right) {
            answer = Math.max(answer, getArea(height, left, right));
            if (height[left] < height[right]) {
                left++;
                continue;
            }
            right--;
        }
        return answer;
    }
}
