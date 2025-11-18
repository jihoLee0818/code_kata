class Solution {
    public int solution(String[] board) {
        int oCount = 0;
        int xCount = 0;

        // 1. O와 X의 개수를 셉니다.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'O') oCount++;
                if (board[i].charAt(j) == 'X') xCount++;
            }
        }

        // [불가능 1] X가 O보다 많을 때
        if (xCount > oCount) return 0;
        
        // [불가능 2] O가 X보다 2개 이상 많을 때
        if (oCount > xCount + 1) return 0;

        // 2. 승리 여부를 확인합니다.
        boolean oWins = checkWin(board, 'O');
        boolean xWins = checkWin(board, 'X');

        // [불가능 3] 둘 다 이겼을 때
        if (oWins && xWins) return 0;
        
        // [불가능 4] O가 이겼는데, O의 개수가 X+1이 아닐 때
        if (oWins && (oCount != xCount + 1)) return 0;

        // [불가능 5] X가 이겼는데, O의 개수가 X와 같지 않을 때
        if (xWins && (oCount != xCount)) return 0;

        // 3. 모든 불가능한 조건을 통과했으면 가능한 상태
        return 1;
    }

    /**
     * 해당 플레이어가 이겼는지 8방향을 확인하는 헬퍼 함수
     */
    private boolean checkWin(String[] board, char player) {
        // 가로 3줄 확인
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == player && 
                board[i].charAt(1) == player && 
                board[i].charAt(2) == player) {
                return true;
            }
        }
        
        // 세로 3줄 확인
        for (int j = 0; j < 3; j++) {
            if (board[0].charAt(j) == player && 
                board[1].charAt(j) == player && 
                board[2].charAt(j) == player) {
                return true;
            }
        }
        
        // 대각선 2줄 확인
        if (board[0].charAt(0) == player && 
            board[1].charAt(1) == player && 
            board[2].charAt(2) == player) {
            return true;
        }
        if (board[0].charAt(2) == player && 
            board[1].charAt(1) == player && 
            board[2].charAt(0) == player) {
            return true;
        }
        
        return false;
    }
}