import java.util.*;

class Solution {
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
        // 좌표 비교를 위한 equals 오버라이딩
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point p = (Point) o;
            return r == p.r && c == p.c;
        }
    }

    int n, m;
    int[][] mazeMap;
    boolean[][] visitedRed;
    boolean[][] visitedBlue;
    int minTurns = Integer.MAX_VALUE;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int solution(int[][] maze) {
        this.mazeMap = maze;
        this.n = maze.length;
        this.m = maze[0].length;
        
        visitedRed = new boolean[n][m];
        visitedBlue = new boolean[n][m];
        
        Point startRed = null, startBlue = null;
        
        // 시작점 찾기
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(maze[i][j] == 1) startRed = new Point(i, j);
                else if(maze[i][j] == 2) startBlue = new Point(i, j);
            }
        }
        
        // 시작 위치 방문 처리
        visitedRed[startRed.r][startRed.c] = true;
        visitedBlue[startBlue.r][startBlue.c] = true;
        
        dfs(startRed, startBlue, 0);
        
        return (minTurns == Integer.MAX_VALUE) ? 0 : minTurns;
    }

    private void dfs(Point curRed, Point curBlue, int count) {
        // 가지치기: 이미 찾은 최단 경로보다 길어지면 중단
        if (count >= minTurns) return;

        boolean redEnd = (mazeMap[curRed.r][curRed.c] == 3);
        boolean blueEnd = (mazeMap[curBlue.r][curBlue.c] == 4);

        // 기저 사례: 둘 다 도착
        if (redEnd && blueEnd) {
            minTurns = Math.min(minTurns, count);
            return;
        }

        // 1. 각 수레의 이동 가능한 다음 위치 후보 리스트 생성
        List<Point> nextRedList = getNextMoves(curRed, visitedRed, redEnd);
        List<Point> nextBlueList = getNextMoves(curBlue, visitedBlue, blueEnd);

        // 2. 이중 루프로 모든 조합 확인 (조합의 수는 최대 4*4=16개로 매우 적음)
        for (Point nextRed : nextRedList) {
            for (Point nextBlue : nextBlueList) {
                
                // [규칙 1] 동시에 같은 칸으로 이동 불가
                if (nextRed.equals(nextBlue)) continue;
                
                // [규칙 2] 서로 자리를 바꾸는 이동 불가 (크로스)
                if (nextRed.equals(curBlue) && nextBlue.equals(curRed)) continue;

                // 유효한 이동이면 방문 처리 후 재귀
                // (주의: 이미 도착한 수레는 방문 배열을 건드리지 않음)
                if (!redEnd) visitedRed[nextRed.r][nextRed.c] = true;
                if (!blueEnd) visitedBlue[nextBlue.r][nextBlue.c] = true;

                dfs(nextRed, nextBlue, count + 1);

                // 백트래킹 (원상 복구)
                if (!redEnd) visitedRed[nextRed.r][nextRed.c] = false;
                if (!blueEnd) visitedBlue[nextBlue.r][nextBlue.c] = false;
            }
        }
    }

    // 이동 가능한 좌표 리스트 반환
    private List<Point> getNextMoves(Point cur, boolean[][] visited, boolean isEnd) {
        List<Point> list = new ArrayList<>();
        
        // 이미 도착했다면 현재 위치에 머무름 (이동 불가)
        if (isEnd) {
            list.add(cur);
            return list;
        }

        // 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nr = cur.r + dr[i];
            int nc = cur.c + dc[i];

            // 격자 범위 내, 벽(5) 아님, 방문하지 않음
            if (nr >= 0 && nr < n && nc >= 0 && nc < m 
                    && mazeMap[nr][nc] != 5 && !visited[nr][nc]) {
                list.add(new Point(nr, nc));
            }
        }
        return list;
    }
}