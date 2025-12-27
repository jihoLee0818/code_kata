import java.util.*;

class Solution {
    // 좌표를 표현하고 정렬하기 위한 클래스
    class Point implements Comparable<Point> {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
        
        // 정렬 기준: 행(r) 오름차순 -> 열(c) 오름차순
        @Override
        public int compareTo(Point o) {
            if (this.r == o.r) return this.c - o.c;
            return this.r - o.r;
        }
    }

    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    int n; // 보드 크기

    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;

        // 1. game_board에서 빈 공간(0) 추출
        List<List<Point>> emptySpaces = extractShapes(game_board, 0);

        // 2. table에서 퍼즐 조각(1) 추출
        List<List<Point>> puzzlePieces = extractShapes(table, 1);

        int answer = 0;
        boolean[] usedPuzzle = new boolean[puzzlePieces.size()];

        // 3. 매칭 시작
        for (List<Point> space : emptySpaces) {
            for (int i = 0; i < puzzlePieces.size(); i++) {
                // 이미 사용한 퍼즐이면 패스
                if (usedPuzzle[i]) continue;

                List<Point> puzzle = puzzlePieces.get(i);
                
                // 개수(칸 수)가 다르면 모양이 같을 수 없음
                if (space.size() != puzzle.size()) continue;

                // 회전하며 매칭 시도
                if (isMatch(space, puzzle)) {
                    answer += space.size(); // 채운 칸 수만큼 점수 추가
                    usedPuzzle[i] = true;   // 퍼즐 사용 처리
                    break; // 해당 공간은 채웠으므로 다음 공간으로 넘어감
                }
            }
        }

        return answer;
    }

    // BFS로 모양 추출하는 함수 (target: 0이면 빈칸, 1이면 퍼즐)
    private List<List<Point>> extractShapes(int[][] board, int target) {
        List<List<Point>> shapes = new ArrayList<>();
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == target && !visited[i][j]) {
                    List<Point> shape = new ArrayList<>();
                    Queue<Point> q = new LinkedList<>();
                    
                    q.offer(new Point(i, j));
                    visited[i][j] = true;
                    shape.add(new Point(i, j));

                    while (!q.isEmpty()) {
                        Point curr = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nr = curr.r + dr[k];
                            int nc = curr.c + dc[k];

                            if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                                if (board[nr][nc] == target && !visited[nr][nc]) {
                                    visited[nr][nc] = true;
                                    Point next = new Point(nr, nc);
                                    q.offer(next);
                                    shape.add(next);
                                }
                            }
                        }
                    }
                    // 추출한 모양을 (0,0) 기준으로 정규화
                    normalize(shape);
                    shapes.add(shape);
                }
            }
        }
        return shapes;
    }

    // 좌표를 (0, 0) 기준으로 맞추고 정렬하는 함수
    private void normalize(List<Point> shape) {
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;

        // 기준점(가장 작은 r, 가장 작은 c) 찾기
        for (Point p : shape) {
            minR = Math.min(minR, p.r);
            minC = Math.min(minC, p.c);
        }

        // 기준점만큼 빼서 이동
        for (Point p : shape) {
            p.r -= minR;
            p.c -= minC;
        }

        // 비교를 위해 항상 정렬 상태 유지
        Collections.sort(shape);
    }

    // 회전을 포함하여 두 모양이 같은지 확인
    private boolean isMatch(List<Point> space, List<Point> puzzle) {
        // 원본 모양 확인
        if (compareShapes(space, puzzle)) return true;

        // 90도씩 3번 더 회전하며 확인
        for (int i = 0; i < 3; i++) {
            rotate(puzzle); // 퍼즐 회전
            if (compareShapes(space, puzzle)) return true;
        }
        return false;
    }

    // 두 좌표 리스트가 정확히 일치하는지 비교
    private boolean compareShapes(List<Point> shape1, List<Point> shape2) {
        for (int i = 0; i < shape1.size(); i++) {
            Point p1 = shape1.get(i);
            Point p2 = shape2.get(i);
            if (p1.r != p2.r || p1.c != p2.c) return false;
        }
        return true;
    }

    // 모양을 90도 회전시키고 다시 정규화하는 함수
    private void rotate(List<Point> shape) {
        for (Point p : shape) {
            // 90도 회전 공식: (r, c) -> (c, -r)
            // 참고: 일반 행렬 회전은 (c, n-1-r)이지만, 
            // 여기서는 모양 자체의 상대적 위치가 중요하고 normalize를 하므로
            // 단순하게 (c, -r)로 변환 후 normalize 해도 됨.
            int temp = p.r;
            p.r = p.c;
            p.c = -temp;
        }
        normalize(shape);
    }
}