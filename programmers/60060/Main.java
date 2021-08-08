import java.util.HashMap;
import java.util.Map;

class Solution {

    public int[] solution(String[] words, String[] queries) {
        Map<Integer, Trie> tries = new HashMap<>();
        Map<Integer, Trie> reverseTries = new HashMap<>();
        for (String word : words) {
            int key = word.length();

            Trie trie = tries.getOrDefault(key, new Trie());
            trie.insert(word);
            tries.put(key, trie);

            Trie reverseTrie = reverseTries.getOrDefault(key, new Trie());
            reverseTrie.insert(new StringBuilder(word).reverse().toString());
            reverseTries.put(key, reverseTrie);
        }
        int[] answer = new int[queries.length];
        for (int index = 0; index < queries.length; index++) {
            String query = queries[index];
            int key = query.length();
            if (!tries.containsKey(key)) {
                answer[index] = 0;
                continue;
            }
            if (isReverseQuery(query)) {
                String reversed = new StringBuilder(query).reverse().toString();
                answer[index] = reverseTries.get(key).getMatchedWordCount(reversed);
            } else {
                answer[index] = tries.get(key).getMatchedWordCount(query);
            }
        }
        return answer;
    }

    private boolean isReverseQuery(String query) {
        return query.charAt(0) == '?';
    }
}

class Trie {

    TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.childrenCount++;
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
        }
        node.isLastCharacter = true;
    }

    public int getMatchedWordCount(String query) {
        return getMatchedWordCount(query, 0, root);
    }

    private int getMatchedWordCount(String query, int index, TrieNode node) {
        if (index == query.length()) {
            return node.isLastCharacter ? 1 : 0;
        }
        char c = query.charAt(index);
        if (c == '?') {
            return node.childrenCount;
        }
        else if (node.children.containsKey(c)) {
            return getMatchedWordCount(query, index+1, node.children.get(c));
        }
        return 0;
    }
}

class TrieNode {

    Map<Character, TrieNode> children = new HashMap<>();
    boolean isLastCharacter = false;
    int childrenCount = 0;
}