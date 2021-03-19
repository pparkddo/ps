class Solution {

    private int getGcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        return getGcd(q, p % q);
    }
    
    public long solution(int w, int h) {
        long total = (long) w * h;
        int unavailable = w + h - getGcd(w, h);
        return total - unavailable;
    }
}
