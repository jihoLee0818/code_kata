import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int rows, int cols, int[][] queries) {
        // 1. 배열 초기화
        int[][] matrix = new int[rows][cols];
        int num = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = num++;
            }
        }

        List<Integer> answerList = new ArrayList<>();

        // 2. 쿼리 처리
        for (int[] query : queries) {
            // 2a. 좌표 변환 (1-based -> 0-based)
            int r1 = query[0] - 1;
            int c1 = query[1] - 1;
            int r2 = query[2] - 1;
            int c2 = query[3] - 1;

            // 2b. 회전 시작
            int temp = matrix[r1][c1]; // 시작 값 임시 저장
            int minVal = temp;         // 최솟값 초기화

            // 2c. 4방향 회전
            // 1. 왼쪽 줄 (위로 ⬆)
            for (int i = r1; i < r2; i++) {
                matrix[i][c1] = matrix[i + 1][c1];
                minVal = Math.min(minVal, matrix[i][c1]);
            }

            // 2. 아래쪽 줄 (왼쪽으로 ⬅)
            for (int i = c1; i < c2; i++) {
                matrix[r2][i] = matrix[r2][i + 1];
                minVal = Math.min(minVal, matrix[r2][i]);
            }

            // 3. 오른쪽 줄 (아래로 ⬇)
            for (int i = r2; i > r1; i--) {
                matrix[i][c2] = matrix[i - 1][c2];
                minVal = Math.min(minVal, matrix[i][c2]);
            }

            // 4. 위쪽 줄 (오른쪽으로 ➡)
            for (int i = c2; i > c1; i--) {
                matrix[r1][i] = matrix[r1][i - 1];
                minVal = Math.min(minVal, matrix[r1][i]);
            }

            // 2d. 마지막 값 복원
            matrix[r1][c1 + 1] = temp;

            // 3. 결과 저장
            answerList.add(minVal);
        }

        // 4. 배열 변환 후 반환
        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }
}