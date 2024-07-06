class Solution {

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int answer = 0;
        while (left < right) {
            int lowerHeight = Math.min(height[left], height[right]);
            answer = Math.max(answer, lowerHeight * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return answer;
    }
}
