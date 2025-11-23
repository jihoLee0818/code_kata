class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int d_balance = 0; // 현재 처리해야 할 배달 물량
        int p_balance = 0; // 현재 처리해야 할 수거 물량
        
        // 가장 먼 집(n-1)부터 0번째 집까지 역순으로 순회
        for (int i = n - 1; i >= 0; i--) {
            d_balance += deliveries[i];
            p_balance += pickups[i];
            
            // 배달할 짐이나 수거할 짐이 남아있다면 (양수라면), 트럭이 와야 함
            while (d_balance > 0 || p_balance > 0) {
                // 1. 트럭이 1번 왔다 갔다 할 때마다 cap만큼 짐을 처리 가능
                d_balance -= cap;
                p_balance -= cap;
                
                // 2. 거리 누적 (왕복이므로 거리 * 2)
                // i번째 집의 거리는 (i + 1)
                answer += (i + 1) * 2;
            }
        }
        
        return answer;
    }
}