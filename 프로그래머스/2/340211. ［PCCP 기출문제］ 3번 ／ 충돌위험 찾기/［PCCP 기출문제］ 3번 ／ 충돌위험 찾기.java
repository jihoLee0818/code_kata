import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        // 모든 로봇의 이동 경로를 저장할 리스트
        // robotPaths.get(i) : i번 로봇의 전체 경로 (시간순 좌표 리스트)
        List<List<int[]>> robotPaths = new ArrayList<>();
        
        // 1. 각 로봇별 상세 이동 경로 계산
        for (int[] route : routes) {
            List<int[]> path = new ArrayList<>();
            
            // 시작점 초기화 (문제의 route 인덱스는 1부터 시작하므로 -1 처리)
            int startPointIdx = route[0] - 1;
            int r = points[startPointIdx][0];
            int c = points[startPointIdx][1];
            
            // 0초(시작 시점) 위치 추가
            path.add(new int[]{r, c});
            
            // 경유지를 순서대로 방문
            for (int i = 1; i < route.length; i++) {
                int nextPointIdx = route[i] - 1;
                int targetR = points[nextPointIdx][0];
                int targetC = points[nextPointIdx][1];
                
                // r 좌표 우선 이동 (행 이동)
                while (r != targetR) {
                    if (r < targetR) r++;
                    else r--;
                    path.add(new int[]{r, c});
                }
                
                // c 좌표 이동 (열 이동)
                while (c != targetC) {
                    if (c < targetC) c++;
                    else c--;
                    path.add(new int[]{r, c});
                }
            }
            robotPaths.add(path);
        }
        
        // 2. 시간대별 충돌 횟수 계산
        int answer = 0;
        int maxTime = 0;
        for (List<int[]> path : robotPaths) {
            maxTime = Math.max(maxTime, path.size());
        }
        
        // 시간 t를 0부터 최대 시간까지 흐르게 함
        for (int t = 0; t < maxTime; t++) {
            // 해당 시간(t)에 각 좌표별 로봇 개수 카운트
            // 맵 크기가 최대 100x100이므로 101x101 배열 사용
            int[][] map = new int[101][101];
            
            for (List<int[]> path : robotPaths) {
                // 해당 로봇이 아직 이동 중이라면
                if (t < path.size()) {
                    int[] pos = path.get(t);
                    map[pos[0]][pos[1]]++;
                }
            }
            
            // 겹치는 지점(2대 이상) 확인
            for (int i = 1; i <= 100; i++) {
                for (int j = 1; j <= 100; j++) {
                    if (map[i][j] > 1) {
                        answer++;
                    }
                }
            }
        }
        
        return answer;
    }
}