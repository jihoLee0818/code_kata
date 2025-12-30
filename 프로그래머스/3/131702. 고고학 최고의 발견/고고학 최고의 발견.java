import java.util.*;

class Solution {
    int N;
    int[][] map;
    int[] rot; // 첫 번째 행의 회전 횟수를 저장할 배열
    int minAnswer = Integer.MAX_VALUE;
    
    // 상, 하, 좌, 우, 자기자신
    int[] dr = {-1, 1, 0, 0, 0};
    int[] dc = {0, 0, -1, 1, 0};

    public int solution(int[][] clockHands) {
        this.map = clockHands;
        this.N = clockHands.length;
        this.rot = new int[N];

        // 1. 첫 번째 행의 회전 조합을 모두 만드는 DFS 시작
        dfs(0);

        return minAnswer;
    }

    // 첫 번째 행의 col번째 칸을 0~3번 회전시키는 모든 경우 탐색
    private void dfs(int col) {
        if (col == N) {
            // 첫 번째 행의 회전수가 모두 결정되면 시뮬레이션 수행
            simulate();
            return;
        }

        for (int i = 0; i < 4; i++) {
            rot[col] = i;
            dfs(col + 1);
        }
    }

    // 결정된 첫 행(rot 배열)을 기준으로 전체 그리드 시뮬레이션
    private void simulate() {
        // 원본 배열 보존을 위해 복사본 생성
        int[][] tempMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            tempMap[i] = map[i].clone();
        }

        int totalCount = 0;

        // 1. DFS로 결정된 '첫 번째 행' 회전 적용
        for (int c = 0; c < N; c++) {
            if (rot[c] > 0) {
                rotate(tempMap, 0, c, rot[c]);
                totalCount += rot[c];
            }
        }

        // 2. 두 번째 행부터 마지막 행까지 '위쪽 행'을 0으로 만들기 위해 회전
        for (int r = 1; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // 바로 위 칸(r-1, c)의 현재 상태 확인
                int currentVal = tempMap[r - 1][c];
                
                // 위 칸이 0(12시)이 아니라면, 현재 칸(r, c)을 돌려서 맞춰야 함
                // 예: 위 칸이 1(3시)이면 -> 3번 돌려서 0으로 만듦
                // 예: 위 칸이 3(9시)이면 -> 1번 돌려서 0으로 만듦
                int needed = (4 - currentVal) % 4;
                
                if (needed > 0) {
                    rotate(tempMap, r, c, needed);
                    totalCount += needed;
                }
            }
        }

        // 3. 마지막 행 검사: 모두 0이 되었는지 확인
        if (checkLastRow(tempMap)) {
            minAnswer = Math.min(minAnswer, totalCount);
        }
    }

    // (r, c)를 cnt번 회전시키는 함수 (주변 4방향 + 자신 영향)
    private void rotate(int[][] arr, int r, int c, int cnt) {
        for (int i = 0; i < 5; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                arr[nr][nc] = (arr[nr][nc] + cnt) % 4;
            }
        }
    }

    // 마지막 행이 모두 0인지 확인
    private boolean checkLastRow(int[][] arr) {
        for (int c = 0; c < N; c++) {
            if (arr[N - 1][c] != 0) return false;
        }
        return true;
    }
}