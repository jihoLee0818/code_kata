import java.util.*;

class Solution {
    int n;
    int[][] q;
    int[] ans;
    int totalCount = 0;
    
    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;
        
        // 1부터 n까지의 숫자 중 선택 여부를 저장할 배열 (인덱스 1~n 사용)
        // Set을 쓰는 것보다 boolean 배열이 훨씬 빠릅니다.
        boolean[] visited = new boolean[n + 1];
        
        // 백트래킹 시작: 1번 숫자부터 고려, 현재 선택된 개수 0개
        combination(1, 0, visited);
        
        return totalCount;
    }
    
    // start: 탐색을 시작할 숫자 (중복 방지 및 오름차순 유지)
    // depth: 현재까지 선택한 숫자의 개수
    // visited: 현재 선택된 숫자들을 표시한 배열
    private void combination(int start, int depth, boolean[] visited) {
        // 1. 기저 사례: 5개의 숫자를 모두 뽑았을 때
        if (depth == 5) {
            if (check(visited)) {
                totalCount++;
            }
            return;
        }
        
        // 2. 조합 생성 (백트래킹)
        for (int i = start; i <= n; i++) {
            visited[i] = true;
            combination(i + 1, depth + 1, visited);
            visited[i] = false; // 백트래킹 (원상복구)
        }
    }
    
    // 현재 뽑은 조합(visited)이 모든 힌트를 만족하는지 검사
    private boolean check(boolean[] visited) {
        // 모든 힌트(q)를 순회
        for (int i = 0; i < q.length; i++) {
            int matchCount = 0;
            
            // 힌트에 있는 5개 숫자가 내 조합(visited)에 있는지 확인
            for (int num : q[i]) {
                if (visited[num]) {
                    matchCount++;
                }
            }
            
            // 힌트에서 제시한 일치 개수(ans[i])와 다르면 실패
            if (matchCount != ans[i]) {
                return false;
            }
        }
        
        // 모든 힌트를 통과하면 성공
        return true;
    }
}