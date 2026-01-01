class Solution {
    public int solution(int n) {
        int MOD = 1000000007;
        
        long[] dp = new long[n + 1];
        
        // 1. 초기값 설정
        // A[1]=1, A[2]=2, A[3]=5
        dp[0] = 1;
        if (n >= 1) dp[1] = 1;
        if (n >= 2) dp[2] = 3;  // 1*1 + 1*2
        if (n >= 3) dp[3] = 10; // 1*3 + 1*2 + 1*5
        
        if (n < 4) return (int) dp[n];
        
        // 2. 누적 합 변수
        long sumTotal = 0;             // 모든 k >= 4 에 대해 (계수 2)
        long[] sumSpecial = new long[3]; // k = 6, 9, 12... 에 대해 (추가 계수 2)
        
        for (int i = 4; i <= n; i++) {
            // sumTotal: 4칸 전의 값을 누적 (모든 i에 대해 공통)
            sumTotal = (sumTotal + dp[i - 4]) % MOD;
            
            // sumSpecial: 6칸 전의 값을 누적 (나머지에 따라 분리 관리)
            if (i >= 6) {
                int rem = i % 3;
                sumSpecial[rem] = (sumSpecial[rem] + dp[i - 6]) % MOD;
            }
            
            // 3. 점화식 계산
            // 기본 1, 2, 3칸 전 (계수 1, 2, 5)
            long val = (dp[i - 1] * 1 + dp[i - 2] * 2 + dp[i - 3] * 5) % MOD;
            
            // 4칸 이상 떨어진 모든 경우 (기본 계수 2)
            val = (val + sumTotal * 2) % MOD;
            
            // 6, 9, 12...칸 떨어진 경우 (추가 계수 2)
            // 현재 i와 나머지가 같은 인덱스만 가져옴
            val = (val + sumSpecial[i % 3] * 2) % MOD;
            
            dp[i] = val;
        }
        
        return (int) dp[n];
    }
}