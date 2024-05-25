import java.util.LinkedList;

class Solution {

    public String simplifyPath(String path) {
        LinkedList<String> list = new LinkedList<>();

        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == '/') {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            while (i < path.length() && path.charAt(i) != '/') {
                sb.append(path.charAt(i++));
            }
            String dir = sb.toString();
            if (".".equals(dir)) {
                continue;
            }
            if ("..".equals(dir)) {
                if (!list.isEmpty()) {
                    list.removeLast();
                }
                continue;
            }
            list.add(dir);
        }

        if (list.isEmpty()) {
            return "/";
        }

        StringBuilder answer = new StringBuilder();
        for (String p : list) {
            answer.append("/").append(p);
        }
        return answer.toString();
    }
}
