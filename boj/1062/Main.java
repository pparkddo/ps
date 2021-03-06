import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int MAX_ALPHABET_COUNT = 26;
    private static final int FIXED_WORD_COUNT = 4;

    private static int solution(int n, int k, List<String> words) {
        List<Character> charactersToBeIncluded = List.of('a', 'c', 'i', 't', 'n');
        if (k < charactersToBeIncluded.size()) {
            return 0;
        }
        if (k == MAX_ALPHABET_COUNT) {
            return n;
        } 
        List<String> trimmed = trimWords(words);
        int mask = getMask(charactersToBeIncluded);
        List<Integer> usedAlphabetMasks = getUsedAlphabetMasks(trimmed);
        return getMaximumReadableCount(0, 0, k - charactersToBeIncluded.size(), mask, usedAlphabetMasks);
    }

    private static List<Integer> getUsedAlphabetMasks(List<String> words) {
        List<Integer> usedAlphabetMasks = new ArrayList<>();
        for (String word : words) {
            int mask = 0;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                mask |= (1 << index);
            }
            usedAlphabetMasks.add(mask);
        }
        return usedAlphabetMasks;
    }

    private static int getMask(List<Character> charactersToBeIncluded) {
        int mask = 0;
        for (Character c : charactersToBeIncluded) {
            mask |= (1 << c-'a');
        }
        return mask;
    }

    private static List<String> trimWords(List<String> words) {
        List<String> trimmed = new ArrayList<>();
        for (String word : words) {
            trimmed.add(word.substring(FIXED_WORD_COUNT, word.length()-FIXED_WORD_COUNT));
        }
        return trimmed;
    }

    private static int getMaximumReadableCount(int start, int depth, int k, int mask, List<Integer> usedAlphabetMasks) {
        if (depth == k) {
            return getReadableCount(mask, usedAlphabetMasks);
        }
        int count = 0;
        for (int index = start; index < MAX_ALPHABET_COUNT; index++) {
            if ((mask & (1 << index)) != 0) {
                continue;
            }
            mask |= (1 << index);
            count = Math.max(count, getMaximumReadableCount(index+1, depth+1, k, mask, usedAlphabetMasks));
            mask &= ~(1 << index);
        }
        return count;
    }

    private static int getReadableCount(int mask, List<Integer> usedAlphabetMasks) {
        int count = 0;
        for (Integer each : usedAlphabetMasks) {
            if (isContainsAll(mask, each)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isContainsAll(int mask, int test) {
        return (mask | test) == mask;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = in.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        List<String> words = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            words.add(in.readLine());
        }
        in.close();
        System.out.println(solution(n, k, words));
    }
}
