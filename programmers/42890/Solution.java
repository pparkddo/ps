// https://programmers.co.kr/learn/courses/30/lessons/42890
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Solution {

    int rowLength;
    int columnLength;
    String[][] relation;
    List<List<Integer>> candidates = new ArrayList<>();

    private String getConcatenatedRow(int rowIndex, List<Integer> columns) {
        StringBuilder concatenated = new StringBuilder();
        for (Integer column : columns) {
            concatenated.append(relation[rowIndex][column]).append(" ");
        }
        return concatenated.toString();
    }

    private boolean isCandidateKey(List<Integer> node) {
        Set<String> rows = new HashSet<String>();
        for (int i = 0; i < rowLength; i++) {
            rows.add(getConcatenatedRow(i, node));
        }
        return rows.size() == rowLength;
    }

    private void bfs() {
        Queue<List<Integer>> queue = new LinkedList<>();
        for (int i = 0; i < columnLength; i++) {
            List<Integer> node = new ArrayList<>();
            node.add(i);
            queue.add(node);
        }
        while (!queue.isEmpty()) {
            List<Integer> node = queue.poll();
            if (isCandidateKey(node)) {
                candidates.add(node);
            }
            for (int i = node.get(node.size()-1); i < columnLength-1; i++) {
                List<Integer> nextNode = new ArrayList<>(node);
                nextNode.add(i+1);
                queue.add(nextNode);
            }
        }
    }

    public int solution(String[][] relation) {
        this.relation = relation;
        rowLength = relation.length;
        columnLength = relation[0].length;
        bfs();
        Collections.sort(candidates, (a, b) -> Integer.compare(a.size(), b.size()));
        List<List<Integer>> answer = new ArrayList<>();
        for (List<Integer> candidate : candidates) {
            boolean isMinimum = true;
            for (List<Integer> each : answer) {
                if (candidate.containsAll(each)) {
                    isMinimum = false;
                    continue;
                }
            }
            if (isMinimum) {
                answer.add(candidate);
            }
        }
        return answer.size();
    }   
}
