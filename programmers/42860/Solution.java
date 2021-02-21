class Solution {
    
    private int getDistance(char each) {
        int forward = Math.abs((int)'A'-(int)each);
        int backward = Math.abs((int)'Z'-(int)each+1);
        return Math.min(forward, backward);
    }
    
    private boolean isEnd(char[] characters) {
        for (char each : characters) {
            if (each != 'A') {
                return false;
            }
        }
        return true;
    }
    
    private int getLeftIndex(char[] characters, int index) {
        return index == 0 ? characters.length-1 : index - 1;
    }
    
    private int getRightIndex(char[] characters, int index) {
        return index == characters.length - 1 ? 0 : index + 1;
    }
    
    public int solution(String name) {
        int answer = 0;
        char[] characters = name.toCharArray();
        
        int index = 0;
        while (true) {
            int distance = getDistance(characters[index]);
            answer += distance;
            characters[index] = 'A';
            
            if (isEnd(characters)) {
                break;
            }
            
            int leftMoveCount = 0;
            int leftIndex = index;
            while (true) {
            	leftIndex = getLeftIndex(characters, leftIndex);
                leftMoveCount++;
                if (characters[leftIndex] != 'A') {
                    break;
                }
            }
            
            int rightMoveCount = 0;
            int rightIndex = index;
            while (true) {
            	rightIndex = getRightIndex(characters, rightIndex);
                rightMoveCount++;
                if (characters[rightIndex] != 'A') {
                    break;
                }
            }
            
            if (leftMoveCount < rightMoveCount) {
                index = leftIndex;
                answer += leftMoveCount;
                continue;
            }
            index = rightIndex;
            answer += rightMoveCount;
        }
        return answer;
    }
}

class Main {

    public static void main(String[] args) {
        System.out.println(
            new Solution().solution("JAN")  // 23
        );
        System.out.println(
            new Solution().solution("J")  // 9
        );
        System.out.println(
            new Solution().solution("AAAJAA")  // 12
        );
        System.out.println(
            new Solution().solution("AABAAAAAAABBB")  // 12
        );
    }
}