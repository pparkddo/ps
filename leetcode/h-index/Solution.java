class Solution {

    public int hIndex(int[] citations) {
        int l = citations.length;
        int[] count = new int[l + 1];

        for (int citation : citations) {
            if (citation >= l) {
                count[l]++;
            } else {
                count[citation]++;
            }
        }

        int total = 0;
        for (int i = l; i >= 0; i--) {
            total += count[i];
            if (total >= i) {
                return i;
            }
        }
        return 0;
    }
}
