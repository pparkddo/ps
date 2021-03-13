class Result {

    public static int countingValleys(int steps, String path) {
        int count = 0;
        int level = 0;
        for (char each : path.toCharArray()) {
            int current = each == 'U' ? 1 : -1;
            if (level == 0 && current == -1) {
                count++;                
            }
            level += current;
        }
        return count;
    }
}
