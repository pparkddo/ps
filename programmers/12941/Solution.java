import java.util.Arrays;

class Solution {

    private int getSum(int[] A, int[] B) {
        int sum = 0;
        for (int index = 0; index < A.length; index++) {
            sum += A[index] * B[B.length-index-1];
        }
        return sum;
    }

    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        return getSum(A, B);
    }
}
