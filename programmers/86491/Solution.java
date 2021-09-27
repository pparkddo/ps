class Solution {

    public int solution(int[][] sizes) {
        int width = 0;
        int height = 0;
        for (int[] size : sizes) {
            if (size[0] > size[1]) {
                width = Math.max(width, size[0]);
                height = Math.max(height, size[1]);
            } else {
                width = Math.max(width, size[1]);
                height = Math.max(height, size[0]);
            }
        }
        return width * height;
    }
}