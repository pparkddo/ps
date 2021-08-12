class Solution {

    public long solution(int price, int money, int count) {
        return Math.max(((long) Math.pow(count, 2) + count) / 2 * price - money, 0);
    }
}
