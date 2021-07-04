import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {

    public int solution(String word, String[] pages) {
        Map<String, Double> linkScores = new HashMap<>();
        int index = 0;
        List<Page> candidates = new ArrayList<>();
        for (String page : pages) {
            String url = getCurrentUrl(page);
            int keywordCount = getKeywordCount(page, word);
            candidates.add(new Page(index++, url, keywordCount));
            List<String> links = getLinkUrls(page);
            for (String link : links) {
                double score = (double) keywordCount / links.size();
                linkScores.put(link, linkScores.getOrDefault(link, 0.0)+score);
            }
        }
        for (Page each : candidates) {
            each.linkScore = linkScores.getOrDefault(each.url, 0.0);
        }
        return candidates.stream().sorted().findFirst().get().index;
    }

    private int getKeywordCount(String page, String keyword) {
        Pattern keywordPattern = Pattern.compile("(?i)" + keyword);
        Matcher matcher = keywordPattern.matcher(page);
        int count = 0;
        while (matcher.find()) {
            if (isCorrectPrevious(page, matcher.start()) && isCorrectNext(page, matcher.end())) {
                count++;
            }
        }
        return count;
    }

    private boolean isCorrectPrevious(String page, int startIndex) {
        if (startIndex == 0) {
            return true;
        }
        char previous = page.charAt(startIndex-1);
        return !Character.isAlphabetic(previous);
    }

    private boolean isCorrectNext(String page, int endIndex) {
        if (endIndex == page.length()-1) {
            return true;
        }
        char next = page.charAt(endIndex);
        return !Character.isAlphabetic(next);
    }

    private String getCurrentUrl(String page) {
        Pattern currentUrlPattern = Pattern.compile("<meta property=\"og:url\" content=\"");
        Matcher matcher = currentUrlPattern.matcher(page);
        matcher.find();
        return parseUrl(page, matcher.end());
    }

    private List<String> getLinkUrls(String page) {
        Pattern linkUrlPattern = Pattern.compile("<a href=\"");
        Matcher matcher = linkUrlPattern.matcher(page);
        List<String> urls = new ArrayList<>();
        while (matcher.find()) {
            urls.add(parseUrl(page, matcher.end()));
        }
        return urls;
    }

    private String parseUrl(String page, int startIndex) {
        StringBuilder url = new StringBuilder();
        for (int index = startIndex; index < page.length(); index++) {
            char c = page.charAt(index);
            if (c == '"') {
                break;
            }
            url.append(c);
        }
        return url.toString();
    }
}

class Page implements Comparable<Page> {

    String url;
    int score;
    double linkScore;
    int index;

    Page(int index, String url, int score) {
        this.index = index;
        this.url = url;
        this.score = score;
    }

    public double getTotalScore() {
        return score + linkScore;
    }

    @Override
    public int compareTo(Page o) {
        if (getTotalScore() == o.getTotalScore()) {
            return Integer.compare(index, o.index);
        }
        return -Double.compare(getTotalScore(), o.getTotalScore());
    }
}
