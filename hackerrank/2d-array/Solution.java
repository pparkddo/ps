public class Solution {
    
    private static int getHourGlassSum(int[][] arr, int row, int column) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 0) {
                    continue;                    
                }
                if (i == 1 && j == 2) {
                    continue;
                }
                sum += arr[row+i][column+j];
            }
        }
        return sum;
    }

    static int hourglassSum(int[][] arr) {
        int answer = Integer.MIN_VALUE;
        for (int row = 0; row < arr.length - 2; row++) {
            for (int column = 0; column < arr[0].length - 2; column++) {
                answer = Math.max(answer, getHourGlassSum(arr, row, column));                
            }
        }
        return answer;
    }
}
