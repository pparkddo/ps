import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    private Map<String, Integer> getDictionary() {
        Map<String, Integer> dictionary = new HashMap<>();
        for (int c = 65; c <= 'Z'; c++) {
            int value = c - 'A' + 1;
            dictionary.put(String.valueOf((char)c), value);
        }
        return dictionary;
    }

    private List<Integer> compress(Map<String, Integer> dictionary, String msg) {
        List<Integer> compressed = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        for (int index = 0; index < msg.length(); index++) {
            word.append(msg.charAt(index));
            if (dictionary.containsKey(word.toString())) {
                continue;
            }
            String w = word.substring(0, word.length()-1);
            compressed.add(dictionary.get(w));
            dictionary.put(word.toString(), dictionary.size()+1);
            word.delete(0, word.length()-1);
        }
        compressed.add(dictionary.get(word.toString()));
        return compressed;
    }

    public int[] solution(String msg) {
        Map<String, Integer> dictionary = getDictionary();
        List<Integer> compressed = compress(dictionary, msg);
        return compressed.stream().mapToInt(Integer::valueOf).toArray();
    }
}
