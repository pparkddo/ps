class Solution {

    public String countAndSay(int n) {
        return recursive(n, 1, "1");
    }

    private String recursive(int n, int curr, String value) {
        if (curr == n) {
            return value;
        }
        return recursive(n, curr+1, convert(value));
    }

    private String convert(String value) {
        StringBuilder result = new StringBuilder();
        int count = 0;
        char prevDigit = value.charAt(0);
        char currentDigit;
        for (int index = 0; index < value.length(); index++) {
            currentDigit = value.charAt(index);
            if (prevDigit != currentDigit) {
                result.append(count);
                result.append(prevDigit);
                count = 0;
            }
            if (index == value.length()-1) {
                result.append(prevDigit == currentDigit ? count+1 : 1);
                result.append(currentDigit);
                continue;
            }
            count = count + 1;
            prevDigit = value.charAt(index);
        }
        return result.toString();
    }
}