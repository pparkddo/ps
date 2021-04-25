import java.util.ArrayList;
import java.util.List;

class Solution {

    private File getFile(String s, int index) {
        String head;
        String number;
        String tail;
        int i;
        char[] cs;

        // head 
        cs = s.toCharArray();
        for (i = 0; i < s.length(); i++) {
            char each = cs[i];
            if (Character.isDigit(each)) {
                break;
            }
        }
        head = s.substring(0, i);
        s = s.substring(i, s.length());

        // number
        cs = s.toCharArray();
        for (i = 0; i < s.length(); i++) {
            char each = cs[i];
            if (i == 5) {
                break;
            }
            if (!Character.isDigit(each)) {
                break;
            }
        }
        number = s.substring(0, i);
        s = s.substring(i, s.length());

        // tail
        tail = s;

        return new File(head, number, tail, index);
    }

    public String[] solution(String[] files) {
        List<File> fileList = new ArrayList<>();
        for (int index = 0; index < files.length; index++) {
            fileList.add(getFile(files[index], index));
        }
        return fileList.stream().sorted().map(File::toString).toArray(String[]::new);
    }
}

class File implements Comparable<File> {

    String head;
    String number;
    String tail;
    int index;

    File (String head, String number, String tail, int index) {
        this.head = head;
        this.number = number;
        this.tail = tail;
        this.index = index;
    }

    public int compareTo(File o) {
        String thisLowerHead = this.head.toLowerCase();
        String otherLowerHead = o.head.toLowerCase();
        if (thisLowerHead.compareTo(otherLowerHead) != 0) {
            return thisLowerHead.compareTo(otherLowerHead);
        }

        int thisNumber = Integer.parseInt(this.number);
        int otherNumber = Integer.parseInt(o.number);
        if (Integer.compare(thisNumber, otherNumber) != 0) {
            return Integer.compare(thisNumber, otherNumber);
        }

        return Integer.compare(this.index, o.index);
    }

    public String toString() {
        return head + number + tail;
    }
}
