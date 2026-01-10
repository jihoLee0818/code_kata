import java.util.*;

class Solution {
    int N, M;
    char[][] map;
    boolean[][] isOutside;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        
        // 1. 테두리에 빈 공간 패딩을 추가하여 (N+2) x (M+2) 격자 생성
        map = new char[N + 2][M + 2];
        isOutside = new boolean[N + 2][M + 2];
        
        // 전체를 '0'(빈 공간)으로 초기화
        for (int i = 0; i < N + 2; i++) {
            Arrays.fill(map[i], '0');
        }
        
        // 중앙에 실제 창고 데이터 입력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i + 1][j + 1] = storage[i].charAt(j);
            }
        }
        
        // 2. 초기 외부 공기 영역 표시 (가장자리에서 BFS)
        bfsOutside();
        
        // 3. 요청 처리
        for (String req : requests) {
            char target = req.charAt(0);
            List<int[]> toRemove = new ArrayList<>();
            
            // 제거 대상 탐색
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (map[i][j] == target) {
                        if (req.length() == 1) {
                            // 지게차: 외부와 닿아있는 것만 선택
                            if (isAccessible(i, j)) {
                                toRemove.add(new int[]{i, j});
                            }
                        } else {
                            // 크레인: 모든 타겟 선택
                            toRemove.add(new int[]{i, j});
                        }
                    }
                }
            }
            
            // 일괄 제거
            for (int[] pos : toRemove) {
                map[pos[0]][pos[1]] = '0';
            }
            
            // 제거된 위치를 기점으로 외부 공기 영역 업데이트
            bfsUpdate(toRemove);
        }
        
        // 남은 컨테이너 개수 세기
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j] != '0') {
                    count++;
                }
            }
        }
        return count;
    }
    
    // 해당 위치가 외부 공기(isOutside)와 닿아있는지 확인
    boolean isAccessible(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (isOutside[nr][nc]) return true;
        }
        return false;
    }
    
    // 초기화용 BFS: 테두리(0,0)에서 시작해 연결된 빈 공간을 외부로 표시
    void bfsOutside() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        isOutside[0][0] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                
                if (nr < 0 || nr >= N + 2 || nc < 0 || nc >= M + 2) continue;
                
                if (map[nr][nc] == '0' && !isOutside[nr][nc]) {
                    isOutside[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }
    
    // 업데이트용 BFS: 이번에 제거된 칸들 중 외부와 연결된 곳에서 시작해 공기 확산
    void bfsUpdate(List<int[]> removed) {
        Queue<int[]> q = new LinkedList<>();
        
        for (int[] pos : removed) {
            int r = pos[0];
            int c = pos[1];
            
            // 제거된 칸이 외부와 닿아있다면, 이제 이 칸도 외부가 됨
            if (isAccessible(r, c)) {
                if (!isOutside[r][c]) {
                    isOutside[r][c] = true;
                    q.offer(new int[]{r, c});
                }
            }
        }
        
        // 새로 외부가 된 칸에서부터 인접한 내부 빈 공간들로 전파
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                
                if (nr < 0 || nr >= N + 2 || nc < 0 || nc >= M + 2) continue;
                
                // 빈 공간인데 아직 외부 판정이 안 난 곳(내부 기포)을 외부로 변경
                if (map[nr][nc] == '0' && !isOutside[nr][nc]) {
                    isOutside[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }
}