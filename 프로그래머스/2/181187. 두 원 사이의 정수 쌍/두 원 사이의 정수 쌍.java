class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;

        // 1. x좌표를 1부터 r2(큰 원의 반지름)까지 순회
        // (1사분면 + x축 양의 방향) 영역을 계산
        for (int x = 1; x <= r2; x++) {
            
            // 2. 큰 원(r2)에 대한 최대 y좌표 계산 (내림)
            // r2^2 - x^2 식 사용 (long 형변환 필수)
            long maxY = (long) Math.floor(Math.sqrt((long)r2 * r2 - (long)x * x));
            
            // 3. 작은 원(r1)에 대한 최소 y좌표 계산 (올림)
            long minY = 0;
            if (x < r1) {
                minY = (long) Math.ceil(Math.sqrt((long)r1 * r1 - (long)x * x));
            }
            
            // 4. 해당 x좌표에서 가능한 y 정수의 개수를 누적
            answer += (maxY - minY + 1);
        }

        // 5. 4개 사분면 대칭 적용
        return answer * 4;
    }
}