class Solution {

    private String word1;
    private String word2;
    private int[][] cache;

    public int minDistance(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;

        this.cache = new int[word1.length()][word2.length()];
        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                this.cache[i][j] = -1;
            }
        }

        return this.traverse(0, 0);
    }

    public int traverse(int leftIndex, int rightIndex) {
        if (leftIndex == word1.length()) {
            return word2.length() - rightIndex;
        }

        if (rightIndex == word2.length()) {
            return word1.length() - leftIndex;
        }

        if (cache[leftIndex][rightIndex] != -1) {
            return cache[leftIndex][rightIndex];
        }

        int c;

        if (word1.charAt(leftIndex) == word2.charAt(rightIndex)) {
            c = traverse(leftIndex + 1, rightIndex + 1);
        } else {
            int insertion = traverse(leftIndex, rightIndex + 1);
            int deletion = traverse(leftIndex + 1, rightIndex);
            int modification = traverse(leftIndex + 1, rightIndex + 1);
            c = Math.min(insertion, Math.min(deletion, modification)) + 1;
        }

        cache[leftIndex][rightIndex] = c;

        return c;
    }
}
