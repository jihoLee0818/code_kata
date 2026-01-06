import java.util.Arrays;

class Solution {
    public int solution(int[][] info, int n, int m) {

        int size = info.length;
        // dp[b] = 현재까지 B가 b 만큼 흔적을 남겼을 때 A가 남긴 최소 흔적
        int[] dp = new int[m];
        final int INF = Integer.MAX_VALUE / 2;

        Arrays.fill(dp, INF);

        // 초기 상태: 아직 아무 물건 안 훔침 → B 흔적 0, A 흔적 0
        dp[0] = 0;

        for (int i = 0; i < size; i++) {
            int a = info[i][0];
            int b = info[i][1];

            int[] nextDp = new int[m];
            Arrays.fill(nextDp, INF);

            for (int curB = 0; curB < m; curB++) {
                if (dp[curB] == INF) continue;

                // A가 훔치는 경우
                if (dp[curB] + a < n) {
                    nextDp[curB] = Math.min(nextDp[curB], dp[curB] + a);
                }

                // B가 훔치는 경우
                int newB = curB + b;
                if (newB < m) {
                    nextDp[newB] = Math.min(nextDp[newB], dp[curB]);
                }
            }

            dp = nextDp;
        }

        int answer = INF;

        // 가능한 B 흔적 상태들 중
        // dp[b]가 n보다 작으면 A 흔적 최소값
        for (int curB = 0; curB < m; curB++) {
            if (dp[curB] < n) {
                answer = Math.min(answer, dp[curB]);
            }
        }

        return (answer == INF) ? -1 : answer;
    }
}
