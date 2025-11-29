import java.util.*;

class Solution {
    
    // 큐에 넣을 노드 클래스 (단어와 현재 변환 횟수 저장)
    static class Node {
        String word;
        int count;
        
        Node(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        // 1. target이 words 안에 없으면 바로 0 반환 (불가능)
        boolean containsTarget = false;
        for (String w : words) {
            if (w.equals(target)) {
                containsTarget = true;
                break;
            }
        }
        if (!containsTarget) return 0;
        
        // 2. BFS를 위한 Queue와 방문 배열 초기화
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        
        // 시작 단어 큐에 추가
        queue.offer(new Node(begin, 0));
        
        // 3. BFS 탐색
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            
            // target 도달 시 변환 횟수 반환
            if (current.word.equals(target)) {
                return current.count;
            }
            
            // words 배열을 순회하며 다음 단계로 갈 수 있는 단어 찾기
            for (int i = 0; i < words.length; i++) {
                // 아직 방문하지 않았고, 변환 가능한(철자가 1개만 다른) 경우
                if (!visited[i] && canConvert(current.word, words[i])) {
                    visited[i] = true; // 방문 처리
                    queue.offer(new Node(words[i], current.count + 1));
                }
            }
        }
        
        return 0; // 변환할 수 없는 경우
    }
    
    // 두 단어가 알파벳 하나만 다른지 확인하는 헬퍼 함수
    private boolean canConvert(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
        }
        return diff == 1; // 정확히 1개만 달라야 함
    }
}