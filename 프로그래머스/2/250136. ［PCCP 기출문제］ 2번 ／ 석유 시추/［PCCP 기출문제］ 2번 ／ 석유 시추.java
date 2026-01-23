import java.util.*;

class Solution {
    int n, m;
    int[] dr = {-1, 1, 0, 0}; // 상하좌우
    int[] dc = {0, 0, -1, 1};
    boolean[][] visited;
    int[] oilSum; // 각 열(column)별로 얻을 수 있는 총 석유량을 저장

    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        visited = new boolean[n][m];
        oilSum = new int[m];

        // 전체 격자를 순회하며 석유 덩어리 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    bfs(land, i, j);
                }
            }
        }

        // 각 열에 누적된 석유량 중 최댓값 찾기
        int maxOil = 0;
        for (int val : oilSum) {
            maxOil = Math.max(maxOil, val);
        }
        return maxOil;
    }

    private void bfs(int[][] land, int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});
        visited[r][c] = true;

        int size = 0;
        // 이 덩어리가 존재하는 열(column)의 인덱스를 중복 없이 저장하기 위해 Set 사용
        Set<Integer> columns = new HashSet<>();

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];

            size++; // 덩어리 크기 증가
            columns.add(curC); // 현재 열 위치 기록

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nr = curR + dr[i];
                int nc = curC + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && 
                    land[nr][nc] == 1 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        // BFS 종료 후: 발견한 덩어리의 크기를, 덩어리가 포함된 모든 열에 더해줌
        for (int col : columns) {
            oilSum[col] += size;
        }
    }
}