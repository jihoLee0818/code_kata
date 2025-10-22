class Solution {
    public int[] solution(int n) {
        // 1. 달팽이집(2D 배열)과 결과(1D 배열) 생성
        int[][] triangle = new int[n][n];
        int maxNum = n * (n + 1) / 2;
        int[] answer = new int[maxNum];

        int r = -1, c = 0; // 현재 위치 (시작은 (0,0)이 되도록 -1에서 시작)
        int num = 1;       // 채울 숫자

        // 2. 이동 시뮬레이션
        for (int i = 0; i < n; i++) {       // i: 방향 제어 (n번 방향이 바뀜)
            for (int j = i; j < n; j++) {   // j: 해당 방향으로 이동하는 횟수 (n, n-1, ...)
                
                if (i % 3 == 0) {
                    // ⬇ 아래로
                    r++;
                } else if (i % 3 == 1) {
                    // ➡ 오른쪽으로
                    c++;
                } else {
                    // ↖ 대각선 위로
                    r--;
                    c--;
                }
                
                triangle[r][c] = num++;
            }
        }

        // 3. 1D 배열로 변환 (0이 아닌 값만 순서대로)
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[index++] = triangle[i][j];
            }
        }

        return answer;
    }
}