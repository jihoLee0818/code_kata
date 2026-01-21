class Solution {
    public int solution(int n, int[] tops) {
        int MOD = 10007;
        
        // 초기 상태 (가상의 시작점)
        // a: 직전 삼각형이 덮인 상태로 시작 (1가지)
        // b: 직전 삼각형이 안 덮인 상태로 시작 (0가지 - 시작점이므로 불가능)
        int a = 1;
        int b = 0;
        
        for (int i = 0; i < n; i++) {
            int next_a = 0;
            int next_b = 0;
            
            if (tops[i] == 1) {
                // 위쪽 삼각형이 있는 경우
                // a[i+1] = 3*a[i] + 2*b[i]
                next_a = (a * 3 + b * 2) % MOD;
                // b[i+1] = a[i] + b[i]
                next_b = (a + b) % MOD;
            } else {
                // 위쪽 삼각형이 없는 경우
                // a[i+1] = 2*a[i] + b[i]
                next_a = (a * 2 + b) % MOD;
                // b[i+1] = a[i] + b[i]
                next_b = (a + b) % MOD;
            }
            
            a = next_a;
            b = next_b;
        }
        
        return (a + b) % MOD;
    }
}