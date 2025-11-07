import java.util.LinkedList;
import java.util.Queue;

class Solution {
    
    // 맵의 크기 및 맵 자체
    int rows, cols;
    char[][] grid;
    
    // 상하좌우 4방향
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int solution(String[] maps) {
        rows = maps.length;
        cols = maps[0].length();
        grid = new char[rows][cols];
        
        int[] start = new int[2];
        int[] lever = new int[2];
        int[] end = new int[2];

        // 1. 맵 변환 및 S, L, E 위치 찾기
        for (int i = 0; i < rows; i++) {
            grid[i] = maps[i].toCharArray();
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'S') start = new int[]{i, j};
                else if (grid[i][j] == 'L') lever = new int[]{i, j};
                else if (grid[i][j] == 'E') end = new int[]{i, j};
            }
        }

        // 2. BFS 1단계: S -> L
        int time_S_to_L = bfs(start, 'L');
        
        // 3. BFS 2단계: L -> E
        int time_L_to_E = bfs(lever, 'E');

        // 4. 결과 반환
        if (time_S_to_L == -1 || time_L_to_E == -1) {
            return -1; // 둘 중 하나라도 도달 못하면 실패
        } else {
            return time_S_to_L + time_L_to_E;
        }
    }

    /**
     * BFS 헬퍼 함수
     * @param start    시작 좌표 {r, c}
     * @param targetChar 목표 지점의 문자 ('L' 또는 'E')
     * @return 최단 거리 (불가능 시 -1)
     */
    private int bfs(int[] start, char targetChar) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        // 큐에 {행, 열, 현재까지의 거리}를 저장
        queue.offer(new int[]{start[0], start[1], 0});
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int dist = current[2];

            // 목표 지점에 도달했으면 거리 반환
            if (grid[r][c] == targetChar) {
                return dist;
            }

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 맵 범위 체크, 벽('X') 체크, 방문 여부 체크
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols &&
                    grid[nr][nc] != 'X' && !visited[nr][nc]) {
                    
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc, dist + 1});
                }
            }
        }
        
        // 큐가 비워질 때까지 목표 지점에 도달하지 못함
        return -1;
    }
}