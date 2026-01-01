import java.util.*;

class Solution {
    // DP에서 사용할 무한대 값 (충분히 큰 값 설정)
    final int INF = 1_000_000;

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        // 1. 온도 범위 보정 (-10~40 -> 0~50)
        int offset = 10;
        temperature += offset;
        t1 += offset;
        t2 += offset;

        int len = onboard.length;
        // dp[i][j] : i분에 온도 j일 때 최소 비용
        // 온도는 0~50도 범위이므로 크기 52로 설정
        int[][] dp = new int[len][52];

        // 2. DP 배열 초기화
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], INF);
        }

        // 0분(시작)에는 실외 온도와 동일하며 비용은 0
        dp[0][temperature] = 0;

        // 3. DP 수행
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= 50; j++) {
                // 승객이 탑승 중인데 목표 온도를 벗어난 경우 -> 불가능한 경로
                if (onboard[i] == 1 && (j < t1 || j > t2)) {
                    continue;
                }

                // Case 1: 온도가 1도 상승해서 현재 j가 된 경우 (j-1 -> j)
                if (j - 1 >= 0) {
                    int cost = (j - 1 < temperature) ? 0 : a; // 실외보다 낮으면 자연 상승(0), 아니면 전력(a)
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + cost);
                }

                // Case 2: 온도가 1도 하강해서 현재 j가 된 경우 (j+1 -> j)
                if (j + 1 <= 50) {
                    int cost = (j + 1 > temperature) ? 0 : a; // 실외보다 높으면 자연 하강(0), 아니면 전력(a)
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1] + cost);
                }

                // Case 3: 온도가 유지된 경우 (j -> j)
                int cost = (j == temperature) ? 0 : b; // 실외랑 같으면 자연 유지(0), 아니면 전력(b)
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + cost);
            }
        }

        // 4. 결과 도출: 마지막 시간에 가능한 온도 중 최소 비용 찾기
        int answer = INF;
        for (int j = 0; j <= 50; j++) {
            // 마지막 시간에도 onboard 체크가 있다면 유효한 범위만 확인해야 하지만,
            // 이미 DP 루프 안에서 onboard[i]==1 일 때 처리를 했으므로
            // dp[len-1][j]에 값이 있다면 유효한 값임.
            if (onboard[len - 1] == 1 && (j < t1 || j > t2)) continue;
            answer = Math.min(answer, dp[len - 1][j]);
        }

        return answer;
    }
}