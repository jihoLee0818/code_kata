import java.util.LinkedList;
import java.util.Queue;

class Solution {
    
    // 상하좌우 4방향을 위한 배열
    static int[] dr = {-1, 1, 0, 0}; // 행
    static int[] dc = {0, 0, -1, 1}; // 열

    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        // 1. 5개의 대기실을 순회
        for (int i = 0; i < 5; i++) {
            char[][] grid = new char[5][5];
            for (int j = 0; j < 5; j++) {
                grid[j] = places[i][j].toCharArray();
            }
            
            // 5. 최종 판정 (일단 1로 가정하고, 위반 시 0으로 바꿈)
            answer[i] = isDistancingKept(grid);
        }
        return answer;
    }

    // 대기실 하나가 거리두기를 지켰는지 확인하는 함수
    private int isDistancingKept(char[][] grid) {
        // 2. 모든 칸을 순회하며 'P'를 찾음
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (grid[r][c] == 'P') {
                    // 3. 'P'를 찾으면 BFS 시작
                    if (!bfs(grid, r, c)) {
                        return 0; // 규칙 위반
                    }
                }
            }
        }
        return 1; // 모든 'P'가 규칙을 지킴
    }

    // 4. BFS 탐색 로직
    private boolean bfs(char[][] grid, int startR, int startC) {
        Queue<int[]> queue = new LinkedList<>();
        // 큐에 {행, 열, 거리}를 저장
        queue.offer(new int[]{startR, startC, 0});
        
        // BFS 탐색마다 visited 배열을 새로 생성
        boolean[][] visited = new boolean[5][5];
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int dist = current[2];

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 맵 범위를 벗어나거나, 이미 방문했으면 건너뜀
                if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5 || visited[nr][nc]) {
                    continue;
                }
                
                // 거리가 2를 초과하면 더 이상 탐색할 필요 없음
                int newDist = dist + 1;
                if (newDist > 2) {
                    continue;
                }
                
                // 파티션('X')을 만나면 막힘
                if (grid[nr][nc] == 'X') {
                    continue;
                }
                
                // 사람('P')을 만나면 거리두기 실패!
                if (grid[nr][nc] == 'P') {
                    return false; 
                }

                // 빈 테이블('O')이면 계속 탐색
                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc, newDist});
            }
        }
        
        // BFS가 끝날 때까지 다른 'P'를 못 찾았으면 성공
        return true;
    }
}