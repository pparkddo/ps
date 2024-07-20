import java.util.Arrays;

class Solution {

    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        chars = reverse(chars, 0, chars.length - 1);
        chars = trim(chars);
        chars = reverseWords(chars);
        return new String(chars);
    }

    private char[] trim(char[] chars) {
        int left = 0;
        int right = 0;

        int n = chars.length;
        while (right < n) {
            while (right < n && chars[right] == ' ') {
                right++;
            }
            while (right < n && chars[right] != ' ') {
                chars[left++] = chars[right++];
            }
            while (right < n && chars[right] == ' ') {
                right++;
            }
            if (right < n) {
                chars[left++] = ' ';
            }
        }

        return Arrays.copyOf(chars, left);
    }

    private char[] reverseWords(char[] chars) {
        int left = 0;
        int right = 0;

        while (left < chars.length && right < chars.length) {
            if (chars[right] != ' ' && right != chars.length - 1) {
                right++;
                continue;
            }

            chars = reverse(chars, left, right != chars.length - 1 ? right - 1 : right);
            right++;
            left = right;
        }

        return chars;
    }

    private char[] reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
        return chars;
    }
}
