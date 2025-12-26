import java.util.*;

class Solution {
    // 0~9번 키의 좌표 (행, 열)
    // 0번 키는 (3, 1) 위치
    int[][] coords = {
        {3, 1}, // 0
        {0, 0}, {0, 1}, {0, 2}, // 1, 2, 3
        {1, 0}, {1, 1}, {1, 2}, // 4, 5, 6
        {2, 0}, {2, 1}, {2, 2}  // 7, 8, 9
    };

    int[][] costs = new int[10][10];
    int[][] dp;

    public int solution(String numbers) {
        // 1. 모든 키 조합에 대한 이동 비용 미리 계산
        initCosts();

        int len = numbers.length();
        // dp[i][j]: i번째 숫자를 눌렀을 때, 움직이지 않은 다른 손이 j에 있을 때의 최소 비용
        dp = new int[len][10];

        // DP 배열 초기화 (큰 값)
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // 2. 초기 상태 설정 (첫 번째 숫자 처리)
        // 시작 위치: 왼손 4, 오른손 6
        int startL = 4;
        int startR = 6;
        int firstNum = numbers.charAt(0) - '0';

        // Case 1: 왼손(4)을 움직여서 첫 숫자를 누름 (오른손은 6 유지)
        if (firstNum != startR) {
            dp[0][startR] = costs[startL][firstNum];
        }
        // Case 2: 오른손(6)을 움직여서 첫 숫자를 누름 (왼손은 4 유지)
        if (firstNum != startL) {
            dp[0][startL] = costs[startR][firstNum];
        }

        // 3. DP 수행
        for (int i = 1; i < len; i++) {
            int curr = numbers.charAt(i) - '0';  // 현재 눌러야 할 숫자
            int prev = numbers.charAt(i - 1) - '0'; // 직전에 눌렀던 숫자 (한 손은 여기에 있음)

            for (int j = 0; j < 10; j++) {
                // 이전 단계에서 유효하지 않은 상태는 건너뜀
                if (dp[i - 1][j] == Integer.MAX_VALUE) continue;

                // Case A: 'prev'에 있던 손을 'curr'로 이동
                // 다른 손(j)은 그대로 유지됨. 조건: 이동할 곳(curr)에 다른 손(j)이 없어야 함
                if (curr != j) {
                    int cost = dp[i - 1][j] + costs[prev][curr];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }

                // Case B: 'j'에 있던 손을 'curr'로 이동
                // 다른 손(prev)은 그대로 유지됨. 조건: 이동할 곳(curr)에 다른 손(prev)이 없어야 함
                if (curr != prev) {
                    int cost = dp[i - 1][j] + costs[j][curr];
                    dp[i][prev] = Math.min(dp[i][prev], cost);
                }
            }
        }

        // 4. 최종 결과: 마지막 단계에서 가능한 모든 상태 중 최솟값
        int minCost = Integer.MAX_VALUE;
        for (int j = 0; j < 10; j++) {
            minCost = Math.min(minCost, dp[len - 1][j]);
        }

        return minCost;
    }

    // 가중치 계산 함수
    private void initCosts() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == j) {
                    costs[i][j] = 1; // 제자리 누르기
                    continue;
                }

                int r1 = coords[i][0], c1 = coords[i][1];
                int r2 = coords[j][0], c2 = coords[j][1];

                int dr = Math.abs(r1 - r2);
                int dc = Math.abs(c1 - c2);
                
                // 대각선 이동(3)을 최대한 많이 하고, 남은 직선 거리(2)를 더함
                // min(dr, dc)만큼 대각선 이동 가능, 나머지는 직선 이동
                int minDiff = Math.min(dr, dc);
                int straightDiff = Math.abs(dr - dc);
                
                costs[i][j] = (minDiff * 3) + (straightDiff * 2);
            }
        }
    }
}