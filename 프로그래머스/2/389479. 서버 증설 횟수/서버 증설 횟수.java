class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        // addedServers[t]: t 시각에 새로 증설한 서버의 개수
        int[] addedServers = new int[24];

        // 0시부터 23시까지 순차적으로 확인
        for (int t = 0; t < 24; t++) {
            // 1. 현재 사용자 수에 대해 필요한 총 증설 서버 수 계산
            // (0~m-1명 -> 0대, m~2m-1명 -> 1대 ...)
            int required = players[t] / m;
            
            // 2. 현재 시점에서 활성화되어 있는 서버 수 계산
            // (현재 시각 t 포함, 지난 k시간 동안 증설된 서버들은 아직 살아있음)
            int currentActive = 0;
            // 시작점: 0시보다 작아질 수 없으므로 Math.max 사용
            for (int i = Math.max(0, t - k + 1); i <= t; i++) {
                currentActive += addedServers[i];
            }
            
            // 3. 서버가 부족하다면 부족한 만큼 즉시 증설
            if (currentActive < required) {
                int diff = required - currentActive;
                addedServers[t] = diff; // t 시각에 diff만큼 증설 기록
                answer += diff;         // 총 증설 횟수 누적
            }
        }
        
        return answer;
    }
}