import java.util.*;

class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        // 1. 초기 불가능 조건 확인
        int distance = Math.abs(x - r) + Math.abs(y - c);
        
        // 거리가 k보다 멀거나, (k - 거리)가 홀수라면 절대 도착 불가
        if (distance > k || (k - distance) % 2 != 0) {
            return "impossible";
        }
        
        StringBuilder sb = new StringBuilder();
        
        // 사전 순 방향 정의: d(아래), l(왼쪽), r(오른쪽), u(위)
        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, -1, 1, 0};
        char[] dChar = {'d', 'l', 'r', 'u'};
        
        // 2. k번 이동 (그리디 탐색)
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];
                
                // 격자 범위 체크
                if (nx > 0 && nx <= n && ny > 0 && ny <= m) {
                    // 남은 이동 횟수 내에 도착 가능한지 확인
                    int nextDist = Math.abs(nx - r) + Math.abs(ny - c);
                    int remainingSteps = k - 1 - i;
                    
                    if (nextDist <= remainingSteps) {
                        // 가능하면 이 방향 확정 (사전 순으로 가장 빠름)
                        sb.append(dChar[j]);
                        x = nx;
                        y = ny;
                        break; // 다음 이동(step)으로 넘어감
                    }
                }
            }
        }
        
        return sb.toString();
    }
}