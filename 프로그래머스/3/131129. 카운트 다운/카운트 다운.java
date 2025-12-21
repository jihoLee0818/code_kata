import java.util.*;

class Solution {
    public int[] solution(int target) {
        // dp[i][0]: i점을 만드는 최소 던진 횟수
        // dp[i][1]: 그때의 최대 싱글/불 횟수
        int[][] dp = new int[target + 1][2];

        // DP 배열 초기화 (최솟값을 구해야 하므로 MAX로 설정)
        for (int i = 0; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            dp[i][1] = 0;
        }
        dp[0][0] = 0; // 0점은 0번 던져서 만듦

        // 점수별 타입(보너스 유무) 미리 계산
        // 0: 불가능한 점수, 1: 싱글/불 (보너스 O), 2: 더블/트리플 (보너스 X)
        int[] scoreType = new int[61];

        // 1. 더블, 트리플 점수 마킹 (보너스 없음)
        for (int i = 1; i <= 20; i++) {
            if (i * 2 <= 60) scoreType[i * 2] = 2;
            if (i * 3 <= 60) scoreType[i * 3] = 2;
        }

        // 2. 싱글 점수 마킹 (보너스 있음, 우선순위 높으므로 덮어쓰기)
        for (int i = 1; i <= 20; i++) {
            scoreType[i] = 1;
        }
        // 3. 불 점수 마킹
        scoreType[50] = 1;

        // DP 수행 (1점부터 target까지)
        for (int i = 1; i <= target; i++) {
            // 한 번의 다트로 낼 수 있는 점수(s)를 순회 (1~60)
            for (int s = 1; s <= 60; s++) {
                // 해당 점수가 다트판에 없으면 패스
                if (scoreType[s] == 0) continue;
                
                // 점수 범위를 벗어나면 확인 중단 (s는 오름차순이므로 break 가능)
                if (i - s < 0) break;

                // 이전 단계가 도달 불가능한 상태라면 패스
                if (dp[i - s][0] == Integer.MAX_VALUE) continue;

                int currentThrows = dp[i - s][0] + 1;
                int currentBonus = dp[i - s][1] + (scoreType[s] == 1 ? 1 : 0);

                // 1. 던진 횟수가 더 적으면 무조건 갱신
                if (currentThrows < dp[i][0]) {
                    dp[i][0] = currentThrows;
                    dp[i][1] = currentBonus;
                }
                // 2. 던진 횟수가 같다면, 싱글/불 횟수가 더 많을 때 갱신
                else if (currentThrows == dp[i][0]) {
                    if (currentBonus > dp[i][1]) {
                        dp[i][1] = currentBonus;
                    }
                }
            }
        }

        return dp[target];
    }
}