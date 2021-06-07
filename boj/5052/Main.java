import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static String solution(int n, String[] numbers) {
        Trie trie = new Trie();
        for (String each : numbers) {
            trie.insert(each);
        }
        return trie.isConsistent() ? "YES" : "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answers = new StringBuilder();
        int t = Integer.parseInt(in.readLine());
        for (int testCase = 0; testCase < t; testCase++) {
            int n = Integer.parseInt(in.readLine());
            String[] numbers = new String[n];
            for (int index = 0; index < numbers.length; index++) {
                numbers[index] = in.readLine();
            }
            answers.append(solution(n, numbers)).append("\n");
        }
        in.close();
        System.out.println(answers.toString().trim());
    }
}

class Trie {

    TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = this.root;

        for (int index = 0; index < word.length(); index++) {
            char c = word.charAt(index);
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
        }

        node.isLastCharacter = true;
    }

    public boolean contains(String word) {
        TrieNode node = this.root;

        for (int index = 0; index < word.length(); index++) {
            char c = word.charAt(index);
            TrieNode child = node.children.get(c);
            if (child == null) {
                return false;
            }
            node = child;
        }
        
        return node.isLastCharacter;
    }

    public void delete(String word) {
        delete(root, word, 0);
    }

    private void delete(TrieNode node, String word, int index) {
        char c = word.charAt(index);

        if (!node.children.containsKey(c)) {
            return;
        }

        TrieNode child = node.children.get(c);
        index++;

        if (index == word.length()) {
            if (!child.isLastCharacter) {
                return;
            }

            child.isLastCharacter = false;
            if (child.children.isEmpty()) {
                node.children.remove(c);
            }
            return;
        }

        delete(child, word, index);
        if (!child.isLastCharacter && child.children.isEmpty()) {
            node.children.remove(c);
        }
    }

    public boolean isConsistent() {
        return isConsistent(root);
    }

    private boolean isConsistent(TrieNode node) {
        if (!node.children.isEmpty() && node.isLastCharacter) {
            return false;
        }
        for (TrieNode child : node.children.values()) {
            if (!isConsistent(child)) {
                return false;
            }
        }
        return true;
    }
}

class TrieNode {

    Map<Character, TrieNode> children = new HashMap<>();
    boolean isLastCharacter;
}