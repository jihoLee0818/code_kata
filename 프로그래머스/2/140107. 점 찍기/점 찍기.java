class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        long d_squared = (long)d * d; // d^2 (long 타입)

        // 1. x좌표를 0부터 d까지 k씩 증가시키며 반복
        for (long x = 0; x <= d; x += k) {
            
            // 2. 최대 y^2 값 계산
            long y_max_squared = d_squared - (x * x);
            
            // 3. 최대 y값 계산
            long y_max = (long)Math.sqrt(y_max_squared);
            
            // 4. y의 개수(0, k, 2k, ...)를 세어 누적
            // (y_max / k)는 몫, +1은 0을 포함하기 위함
            answer += (y_max / k) + 1;
        }
        
        return answer;
    }
}