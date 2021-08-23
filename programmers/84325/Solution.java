import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {

    public String solution(String[] tableBeforeParsed, String[] languages, int[] preference) {
        Table table = Table.of(tableBeforeParsed);
        List<LanguagePreferenceScore> languagePreferenceScores = getLanguagePreferenceScores(table, languages, preference);
        return languagePreferenceScores.stream().sorted().findFirst().orElseThrow().position;
    }

    private List<LanguagePreferenceScore> getLanguagePreferenceScores(Table table, String[] languages, int[] preference) {
        List<LanguagePreferenceScore> languageScores = new ArrayList<>();
        for (String position : table.getAllPositions()) {
            languageScores.add(getLanguagePreferenceScore(table, position, languages, preference));
        }
        return languageScores;
    }

    private LanguagePreferenceScore getLanguagePreferenceScore(Table table, String position, String[] languages, int[] preference) {
        int sum = 0;
        for (int index = 0; index < preference.length; index++) {
            String language = languages[index];
            int p = preference[index];
            sum += table.getScoreByPositionAndLanguage(position, language) * p;
        }
        return new LanguagePreferenceScore(position, sum);
    }
}

class Table {

    private Map<String, Map<String, Integer>> table = new HashMap<>();

    public int getScoreByPositionAndLanguage(String position, String language) {
        return table.get(position).getOrDefault(language, 0);
    }

    public Set<String> getAllPositions() {
        return table.keySet();
    }

    public void addPosition(String position) {
        if (table.containsKey(position)) {
            return;
        }
        table.put(position, new HashMap<>());
    }

    public void addScoreByPositionAndLanguage(String position, String language, int score) {
        table.get(position).put(language, score);
    }

    public static Table of(String[] tableBeforeParsed) {
        Table table = new Table();
        for (String each : tableBeforeParsed) {
            String[] row = each.split(" ");
            String position = row[0];
            table.addPosition(position);
            for (int index = 1; index < row.length; index++) {
                String language = row[index];
                int score = 6 - index;
                table.addScoreByPositionAndLanguage(position, language, score);
            }
        }
        return table;
    }

    @Override
    public String toString() {
        return table.toString();
    }
}

class LanguagePreferenceScore implements Comparable<LanguagePreferenceScore> {

    String position;
    int score;

    LanguagePreferenceScore(String position, int score) {
        this.position = position;
        this.score = score;
    }

    @Override
    public int compareTo(LanguagePreferenceScore o) {
        if (score != o.score) {
            return Integer.compare(o.score, score);
        }
        return position.compareTo(o.position);
    }
}