class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;
        
        // 탐색 범위 설정
        // 최악의 경우: 광물 2*10^9개, 무게제한 1, 편도 10^5초
        // 왕복 2*10^5초 * 2*10^9회 = 4*10^14초
        long start = 0;
        long end = 400000000000000L; 
        
        answer = end;

        while (start <= end) {
            long mid = (start + end) / 2;

            if (isPossible(mid, a, b, g, s, w, t)) {
                answer = mid;       // 가능한 시간을 찾았으므로 저장
                end = mid - 1;      // 더 짧은 시간도 가능한지 탐색 (Left)
            } else {
                start = mid + 1;    // 시간이 부족하므로 늘림 (Right)
            }
        }
        
        return answer;
    }

    // 주어진 시간(time) 내에 목표량(a, b)을 운반할 수 있는지 확인하는 함수
    private boolean isPossible(long time, int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long totalGold = 0;   // 운반 가능한 금의 최대 총량
        long totalSilver = 0; // 운반 가능한 은의 최대 총량
        long totalMix = 0;    // 운반 가능한 (금+은)의 최대 총량

        int n = g.length;

        for (int i = 0; i < n; i++) {
            // 해당 도시의 왕복 시간
            long roundTime = 2L * t[i];
            
            // 왕복 횟수
            long moveCnt = time / roundTime;
            
            // 남은 시간이 편도 시간 이상이면 편도 1회 추가 가능
            if (time % roundTime >= t[i]) {
                moveCnt++;
            }

            // 해당 도시에서 이 시간 동안 나를 수 있는 최대 무게
            long maxWeight = moveCnt * w[i];

            // 1. 금만 최대로 가져갈 경우
            totalGold += Math.min(g[i], maxWeight);
            
            // 2. 은만 최대로 가져갈 경우
            totalSilver += Math.min(s[i], maxWeight);
            
            // 3. 금과 은을 합쳐서 최대로 가져갈 경우
            // (도시의 자원 총량과 트럭의 운반 능력 중 작은 값)
            totalMix += Math.min((long)g[i] + s[i], maxWeight);
        }

        // 세 가지 조건을 모두 만족해야 함
        // 1. 금의 총 운반 가능량이 목표치 a 이상이어야 함
        // 2. 은의 총 운반 가능량이 목표치 b 이상이어야 함
        // 3. 전체 자원 운반 가능량이 (a + b) 이상이어야 함
        return totalGold >= a && totalSilver >= b && totalMix >= (long)a + b;
    }
}