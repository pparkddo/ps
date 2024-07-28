class Solution {

    public int nonSpecialCount(int l, int r) {
        int n = (int) Math.sqrt(1_000_000_000);
        boolean[] isPrime = getPrimeSieve(n);

        int count = 0;

        int rootL = (int) Math.sqrt(l);
        int start = rootL * rootL == l ? rootL : rootL + 1;

        for (int i = start; i <= (int) Math.sqrt(r); i++) {
            if (isPrime[i]) {
                count++;
            }
        }

        return r - l + 1 - count;
    }

    private boolean[] getPrimeSieve(int n) {
        boolean[] prime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            prime[i] = true;
        }

        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }

        return prime;
    }
}
