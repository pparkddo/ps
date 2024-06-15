class Solution {

    private int x;

    public boolean isPalindrome(int x) {
        this.x = x;

        if (x < 0) {
            return false;
        }

        int length = 0;
        while ((x / (int) (Math.pow(10, length))) > 0 && length <= 10) {
            length++;
        }

        return check(length-1, 0);
    }

    private boolean check(int left, int right) {
        if (left <= right) {
            return true;
        }

        if ((x / (int) Math.pow(10, left)) % 10 != (x / (int) Math.pow(10, right)) % 10) {
            return false;
        }
        return check(left-1, right+1);
    }
}