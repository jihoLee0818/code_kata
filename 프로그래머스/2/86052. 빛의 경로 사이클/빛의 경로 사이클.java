import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    
    // 격자 크기
    int R, C;
    // 상, 우, 하, 좌 (시계 방향 순서)
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    boolean[][][] visited;

    public int[] solution(String[] grid) {
        R = grid.length;
        C = grid[0].length();
        visited = new boolean[R][C][4];
        List<Integer> cycles = new ArrayList<>();

        // 모든 좌표와 모든 방향에서 빛을 쏘아본다
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                for (int d = 0; d < 4; d++) {
                    if (!visited[r][c][d]) {
                        // 방문하지 않은 경로라면 시뮬레이션 시작
                        cycles.add(simulate(grid, r, c, d));
                    }
                }
            }
        }

        // 결과를 오름차순 정렬
        Collections.sort(cycles);
        
        return cycles.stream().mapToInt(i -> i).toArray();
    }

    // 빛의 이동 시뮬레이션
    private int simulate(String[] grid, int r, int c, int d) {
        int count = 0; // 이동 거리

        while (!visited[r][c][d]) {
            visited[r][c][d] = true; // 현재 상태 방문 처리
            count++;

            // 1. 현재 격자의 종류에 따라 방향 전환
            char cell = grid[r].charAt(c);
            if (cell == 'L') {
                d = (d + 3) % 4; // 좌회전 (0->3, 1->0, ...)
            } else if (cell == 'R') {
                d = (d + 1) % 4; // 우회전 (0->1, 1->2, ...)
            }
            // 'S'인 경우 방향 유지 (d = d)

            // 2. 다음 좌표로 이동 (격자 밖 처리 포함)
            r = (r + dr[d] + R) % R;
            c = (c + dc[d] + C) % C;
        }

        return count;
    }
}