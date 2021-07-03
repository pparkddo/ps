class Solution {

    private static final int LOSE = -1;
    private static final int UNKNOWN = 0;
    private static final int WIN = 1;

    public int solution(int n, int[][] results) {
        int[][] ranks = getRanks(n, results);
        calculateRank(n, ranks);
        return getDefiniteRankCount(ranks);
    }

    private int getDefiniteRankCount(int[][] ranks) {
        int count = 0;
        for (int[] rank : ranks) {
            count += isDefiniteRank(rank) ? 1 : 0;
        }
        return count;
    }

    private boolean isDefiniteRank(int[] rank) {
        int unknownCount = 0;
        for (int index = 1; index < rank.length; index++) {
            if (rank[index] == UNKNOWN) {
                unknownCount++;
            }
        }
        return unknownCount <= 1;
    }

    private int[][] getRanks(int n, int[][] results) {
        int[][] ranks = new int[n+1][n+1];
        for (int[] result : results) {
            ranks[result[0]][result[1]] = WIN;
            ranks[result[1]][result[0]] = LOSE;
        }
        return ranks;
    }

    private void calculateRank(int n, int[][] ranks) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (ranks[i][j] != UNKNOWN) {
                        continue;
                    }
                    if (ranks[i][k] == WIN && ranks[k][j] == WIN) {
                        ranks[i][j] = WIN;
                    }
                    if (ranks[i][k] == LOSE && ranks[k][j] == LOSE) {
                        ranks[i][j] = LOSE;
                    }
                }
            }
        }
    }
}