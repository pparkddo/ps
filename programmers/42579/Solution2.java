import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Music implements Comparable<Music> {
    
    int id;
    int play;
    String genre;

    Music(int id, int play, String genre) {
        this.id = id;
        this.play = play;
        this.genre = genre;
    }

    @Override
    public int compareTo(Music music) {
        if (this.play == music.play) {
            return -Integer.compare(this.id, music.id);
        }
        return Integer.compare(this.play, music.play);
    }

    @Override
    public String toString() {
        return this.id + " " + this.play + " " + this.genre;
    }
}

class Solution2 {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> counts = new HashMap<>();
        Map<String, PriorityQueue<Music>> musics = new HashMap<>();
        for (int i = 0; i < plays.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            counts.put(genre, counts.getOrDefault(genre, 0)+play);

            PriorityQueue<Music> pq = musics.getOrDefault(genre, new PriorityQueue<>(Collections.reverseOrder()));
            pq.add(new Music(i, play, genre));
            musics.put(genre, pq);
        }

        List<String> sorted = counts
                                .entrySet()
                                .stream()
                                .sorted((a, b) -> -Integer.compare(a.getValue(), b.getValue()))
                                .map(each -> each.getKey())
                                .collect(Collectors.toList());

        List<Integer> answer = new ArrayList<>();
        for (String genre : sorted) {
            for (int i = 0; i < 2; i++) {
                PriorityQueue<Music> pq = musics.get(genre);
                if (pq.isEmpty()) {
                    break;
                }
                answer.add(pq.poll().id);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
