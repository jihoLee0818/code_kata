class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        // 가능한 시작점의 범위를 나타내는 변수 (long 사용 필수)
        // 초기에는 도착점 (x, y) 한 점만 가능
        long top = x, bottom = x;
        long left = y, right = y;

        // 쿼리를 역순으로 순회
        for (int i = queries.length - 1; i >= 0; i--) {
            int command = queries[i][0];
            int dx = queries[i][1];

            if (command == 0) { // 원래: 열 번호 감소 (Left) -> 역: 열 번호 증가 (Right)
                if (left != 0) {
                    left += dx; // 벽에 붙어있지 않았다면 그대로 이동
                }
                // right는 벽 여부와 상관없이 이동 후 격자 크기로 제한
                right = Math.min(m - 1, right + dx);
                
                // 이동한 범위가 격자를 완전히 벗어난 경우 -> 불가능
                if (left > m - 1) return 0;

            } else if (command == 1) { // 원래: 열 번호 증가 (Right) -> 역: 열 번호 감소 (Left)
                if (right != m - 1) {
                    right -= dx; // 벽에 붙어있지 않았다면 그대로 이동
                }
                left = Math.max(0, left - dx);
                
                if (right < 0) return 0;

            } else if (command == 2) { // 원래: 행 번호 감소 (Up) -> 역: 행 번호 증가 (Down)
                if (top != 0) {
                    top += dx;
                }
                bottom = Math.min(n - 1, bottom + dx);
                
                if (top > n - 1) return 0;

            } else { // 원래: 행 번호 증가 (Down) -> 역: 행 번호 감소 (Up)
                if (bottom != n - 1) {
                    bottom -= dx;
                }
                top = Math.max(0, top - dx);
                
                if (bottom < 0) return 0;
            }
        }

        // 최종 범위 내의 칸 개수 계산
        // (행 범위 크기) * (열 범위 크기)
        return (bottom - top + 1) * (right - left + 1);
    }
}