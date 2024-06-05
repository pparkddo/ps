class Solution {

    public void rotate(int[][] matrix) {
        int l = matrix.length;
        for (int i = 0; i < l / 2 + l % 2; i++) {
            for (int j = 0; j < l - (i * 2) - 1; j++) {
                int v = matrix[i][i + j];
                matrix[i][i + j] = matrix[l - 1 - i - j][i];
                matrix[l - 1 - i - j][i] = matrix[l - 1 - i][l - 1 - i - j];
                matrix[l - 1 - i][l - 1 - i - j] = matrix[i + j][l - 1 - i];
                matrix[i + j][l - 1 - i] = v;
            }
        }
    }
}