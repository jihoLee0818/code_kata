import java.util.*;

class Solution {
    int[][] board;
    int N, M;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    // 결과를 담을 클래스
    class Result {
        boolean win; // true: 현재 턴 플레이어 승리, false: 패배
        int step;    // 총 이동 횟수

        public Result(boolean win, int step) {
            this.win = win;
            this.step = step;
        }
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.board = board;
        this.N = board.length;
        this.M = board[0].length;

        // A가 먼저 시작
        Result res = dfs(aloc[0], aloc[1], bloc[0], bloc[1]);
        return res.step;
    }

    // 현재 플레이어(r1, c1)가 이동할 차례
    public Result dfs(int r1, int c1, int r2, int c2) {
        // 1. 현재 서 있는 발판이 이미 사라진 경우 (상대방이 이동하면서 없앰) -> 패배
        if (board[r1][c1] == 0) {
            return new Result(false, 0);
        }

        List<Result> outcomes = new ArrayList<>();
        boolean canMove = false;

        // 2. 4방향 이동 시도
        for (int i = 0; i < 4; i++) {
            int nr = r1 + dx[i];
            int nc = c1 + dy[i];

            if (nr >= 0 && nr < N && nc >= 0 && nc < M && board[nr][nc] == 1) {
                canMove = true;
                
                // 백트래킹: 발판 없애고 이동
                board[r1][c1] = 0;
                
                // 상대방 턴으로 넘김 (위치 파라미터 순서 변경)
                Result res = dfs(r2, c2, nr, nc);
                
                // 발판 복구
                board[r1][c1] = 1;

                // 상대방이 이기면(true) -> 나는 지는 것(false)
                // 상대방이 지면(false) -> 나는 이기는 것(true)
                outcomes.add(new Result(!res.win, res.step + 1));
            }
        }

        // 3. 움직일 곳이 없으면 패배
        if (!canMove) {
            return new Result(false, 0);
        }

        // 4. 최적의 결과 선택 (Win Fast, Lose Slow)
        boolean canWin = false;
        int minWinStep = Integer.MAX_VALUE;
        int maxLoseStep = Integer.MIN_VALUE;

        for (Result res : outcomes) {
            if (res.win) {
                canWin = true;
                // 이길 수 있다면 가장 빨리 이기는 길 선택
                minWinStep = Math.min(minWinStep, res.step);
            } else {
                // 질 수밖에 없다면 가장 최대한 버티는 길 선택
                maxLoseStep = Math.max(maxLoseStep, res.step);
            }
        }

        if (canWin) {
            return new Result(true, minWinStep);
        } else {
            return new Result(false, maxLoseStep);
        }
    }
}