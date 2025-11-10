import java.util.LinkedList;
import java.util.Queue;

class Solution {
    
    // 맵의 크기, 맵 자체
    int R, C;
    char[][] grid;
    
    // 상, 하, 좌, 우
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int solution(String[] board) {
        R = board.length;
        C = board[0].length();
        grid = new char[R][C];
        
        int startR = -1, startC = -1;

        // 맵을 char[][]로 변환하고 시작('R') 위치 찾기
        for (int i = 0; i < R; i++) {
            grid[i] = board[i].toCharArray();
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == 'R') {
                    startR = i;
                    startC = j;
                }
            }
        }

        // BFS 큐와 visited 배열 초기화
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        // 큐에 시작점 추가: {행, 열, 이동 횟수}
        queue.offer(new int[]{startR, startC, 0});
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int moves = current[2];

            // 목표('G')에 도달했으면 횟수 반환
            if (grid[r][c] == 'G') {
                return moves;
            }

            // 4방향으로 미끄러지기
            for (int i = 0; i < 4; i++) {
                // 미끄러진 후 멈춘 위치 (nr, nc) 계산
                int[] nextPos = slide(r, c, i);
                int nr = nextPos[0];
                int nc = nextPos[1];

                // 아직 멈춘 적 없는 위치라면 큐에 추가
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc, moves + 1});
                }
            }
        }
        
        // 큐가 비워질 때까지 G에 도달 못하면 -1
        return -1;
    }

    // (r, c)에서 dir 방향으로 미끄러졌을 때 멈추는 위치를 반환
    private int[] slide(int r, int c, int dir) {
        int nr = r;
        int nc = c;

        // 다음 칸이 (1)맵 밖이 아니고 (2)장애물 'D'가 아닐 때까지 계속 이동
        while (true) {
            int tempR = nr + dr[dir];
            int tempC = nc + dc[dir];

            // 1. 맵 밖인지 확인
            if (tempR < 0 || tempR >= R || tempC < 0 || tempC >= C) {
                break; // 맵 밖이면, 그 전 위치(nr, nc)에서 멈춤
            }
            
            // 2. 장애물 'D'인지 확인
            if (grid[tempR][tempC] == 'D') {
                break; // 장애물이면, 그 전 위치(nr, nc)에서 멈춤
            }

            // 맵 밖도 아니고 장애물도 아니면, 한 칸 이동
            nr = tempR;
            nc = tempC;
        }
        
        return new int[]{nr, nc};
    }
}