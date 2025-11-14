class Solution {
    
    int answer = 0;
    int n;
    // 퀸의 위치를 저장할 배열
    // index = 행(row), value = 열(col)
    // 예: colPlacement[2] = 3 -> 2번 행 3번 열에 퀸이 있다.
    int[] colPlacement; 

    public int solution(int n) {
        this.n = n;
        this.colPlacement = new int[n];
        
        // 0번 행부터 퀸 놓기 시작
        dfs(0);
        
        return answer;
    }

    /**
     * @param row 현재 퀸을 놓으려는 행 번호
     */
    private void dfs(int row) {
        // 2. Base Case: N개의 퀸을 모두 놓았으면
        if (row == n) {
            answer++;
            return;
        }

        // 3. Recursive Step: 0번 열부터 n-1번 열까지 시도
        for (int col = 0; col < n; col++) {
            // 3-1. (row, col)이 안전한지 확인
            if (isSafe(row, col)) {
                colPlacement[row] = col; // 퀸을 놓는다 (위치 기록)
                dfs(row + 1);          // 다음 행으로 이동
                
                // (백트래킹)
                // 이 줄은 사실상 필요 없습니다.
                // 다음 col을 시도할 때 colPlacement[row]의 값이
                // 어차피 덮어써지기(overwrite) 때문입니다.
            }
        }
    }

    /**
     * (row, col) 위치에 퀸을 놓아도 안전한지 확인
     */
    private boolean isSafe(int row, int col) {
        // 0행부터 (row-1)행까지 (이전에 놓은 퀸들) 확인
        for (int prevRow = 0; prevRow < row; prevRow++) {
            int prevCol = colPlacement[prevRow];

            // 1. 세로(Column) 확인
            if (prevCol == col) {
                return false;
            }

            // 2. 대각선(Diagonal) 확인
            if (Math.abs(row - prevRow) == Math.abs(col - prevCol)) {
                return false;
            }
        }
        
        return true;
    }
}