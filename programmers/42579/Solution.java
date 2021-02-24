// https://programmers.co.kr/learn/courses/30/lessons/42579
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Comparator;

class Music implements Comparable<Music> {
    
    int id;
    int play;
    
    Music(int id, int play) {
        this.id = id;
        this.play = play;
    }
    
    @Override
    public int compareTo(Music music) {
        if (this.play == music.play) {
            return -Integer.compare(this.id, music.id);
        }
        return Integer.compare(this.play, music.play);
    }
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> counts = new HashMap<>();
        Map<String, PriorityQueue<Music>> musics = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];
            int count = counts.getOrDefault(genre, 0) + play;
            counts.put(genre, count);
            
            PriorityQueue<Music> music = musics.getOrDefault(
                genre,
                new PriorityQueue<>(Collections.reverseOrder())
            );
            music.add(new Music(i, play));
            musics.put(genre, music);
        }
        List<String> sorted = counts
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .map(each -> each.getKey())
            .collect(Collectors.toList());
        List<Integer> answer = new ArrayList<>();
        for (String genre : sorted) {
            for (int i = 0; i < 2; i++) {
            	PriorityQueue<Music> music = musics.get(genre);
                if (music.isEmpty()) {
                    continue;
                }
            	answer.add(music.poll().id);
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
