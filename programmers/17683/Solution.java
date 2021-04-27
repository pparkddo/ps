import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Solution {
    
    private LocalTime parseTime(String s) {
        int hour = Integer.parseInt(s.substring(0, 2));
        int minute = Integer.parseInt(s.substring(3));
        return LocalTime.of(hour, minute);
    }
    
    private List<Music> getMusics(String[] musicinfos) {
        List<Music> musics = new ArrayList<>();
        for (int id = 0; id < musicinfos.length; id++) {
            String musicinfo = musicinfos[id];
            String[] separated = musicinfo.split(",");
            LocalTime start = parseTime(separated[0]);
            LocalTime end = parseTime(separated[1]);
            Music music = new Music(id, start, end, separated[2], replace(separated[3]));
            musics.add(music);
        }
        return musics;
    }

    private String replace(String s) {
        return s.replaceAll("C#", "0")
                .replaceAll("D#", "1")
                .replaceAll("F#", "2")
                .replaceAll("G#", "3")
                .replaceAll("A#", "4");
    }

    public String solution(String m, String[] musicinfos) {
        String targetMusic = replace(m);
        List<Music> musics = getMusics(musicinfos);
        Optional<Music> answer = musics.stream().filter(each -> each.score.contains(targetMusic)).sorted().findFirst();
        return answer.isPresent() ? answer.get().title : "(None)";
    }
}

class Music implements Comparable<Music> {
    
    int id;
    LocalTime start;
    LocalTime end;
    String title;
    String score;
    int duration;
    
    Music(int id, LocalTime start, LocalTime end, String title, String score) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.title = title;
        this.duration = (int) Duration.between(start, end).toMinutes();
        this.score = getFullScore(score, duration);
    }

    private String getFullScore(String score, int duration) {
        int repeatCount = (int) Math.ceil((double) duration / score.length());
        return score.repeat(repeatCount).substring(0, duration);
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.id)
         .append(" ")
         .append(this.start)
         .append(" ")
         .append(this.end)
         .append(" ")
         .append(this.title)
         .append(" ")
         .append(this.score)
         .append(" ")
         .append(this.duration);
        return s.toString();
    }

    @Override
    public int compareTo(Music o) {
        if (this.duration == o.duration) {
            return Integer.compare(this.id, o.id);
        }
        return -Integer.compare(this.duration, o.duration);
    }
}