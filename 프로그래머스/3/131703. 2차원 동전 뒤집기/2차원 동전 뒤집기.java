class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int n = beginning.length;
        int m = beginning[0].length;
        
        int answer = Integer.MAX_VALUE;
        
        // Case 1: 첫 번째 열을 뒤집지 않고 시작하는 경우
        int res1 = solve(beginning, target, n, m, 0);
        if (res1 != -1) answer = Math.min(answer, res1);
        
        // Case 2: 첫 번째 열을 뒤집고 시작하는 경우
        int res2 = solve(beginning, target, n, m, 1);
        if (res2 != -1) answer = Math.min(answer, res2);
        
        if (answer == Integer.MAX_VALUE) return -1;
        return answer;
    }
    
    // firstColFlip: 0이면 첫 열 안 뒤집음, 1이면 뒤집음
    private int solve(int[][] beg, int[][] tgt, int n, int m, int firstColFlip) {
        int flipCount = 0;
        int[] rowFlipStatus = new int[n]; // 각 행의 뒤집힘 여부 저장 (0: 안함, 1: 함)
        
        // 1. 첫 번째 열 처리
        if (firstColFlip == 1) {
            flipCount++;
        }
        
        // 2. 첫 번째 열의 상태를 맞추기 위해 각 행을 어떻게 해야 하는지 결정
        for (int i = 0; i < n; i++) {
            int currentVal = beg[i][0];
            
            // 첫 열을 뒤집었다면 값 반전
            if (firstColFlip == 1) {
                currentVal = 1 - currentVal;
            }
            
            // 목표와 다르면 해당 행을 반드시 뒤집어야 함
            if (currentVal != tgt[i][0]) {
                rowFlipStatus[i] = 1;
                flipCount++;
            } else {
                rowFlipStatus[i] = 0;
            }
        }
        
        // 3. 결정된 행 상태를 기반으로 나머지 열(1 ~ m-1) 검사
        for (int j = 1; j < m; j++) {
            int sameCount = 0; // 목표와 일치하는 개수
            int diffCount = 0; // 목표와 반대인 개수
            
            for (int i = 0; i < n; i++) {
                int val = beg[i][j];
                // 위에서 결정된 행 뒤집기 적용
                if (rowFlipStatus[i] == 1) {
                    val = 1 - val;
                }
                
                if (val == tgt[i][j]) sameCount++;
                else diffCount++;
            }
            
            // 해당 열이 목표와 완전히 같으면 -> 추가 작업 없음
            if (sameCount == n) {
                continue;
            } 
            // 해당 열이 목표와 완전히 반대면 -> 열을 한번 뒤집으면 됨
            else if (diffCount == n) {
                flipCount++;
            } 
            // 섞여 있으면 -> 불가능한 케이스
            else {
                return -1;
            }
        }
        
        return flipCount;
    }
}