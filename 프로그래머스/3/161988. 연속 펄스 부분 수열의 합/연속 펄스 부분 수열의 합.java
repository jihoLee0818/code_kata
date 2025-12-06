class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        
        // 누적 합을 저장할 변수 (long 사용 필수: int 범위 초과 가능성)
        long pSum = 0;
        
        // 누적 합의 최댓값과 최솟값 초기화
        // 아무것도 선택하지 않은 상태(0)도 포함해야 하므로 0으로 초기화
        long max = 0;
        long min = 0;
        
        int pulse = 1; // 1, -1, 1, -1 ... 순서로 곱해질 변수

        for (int num : sequence) {
            // 1. 펄스 수열 적용하여 누적 합 계산
            pSum += num * pulse;
            pulse *= -1; // 부호 반전
            
            // 2. 최댓값과 최솟값 갱신
            max = Math.max(max, pSum);
            min = Math.min(min, pSum);
        }
        
        // 3. (최대 - 최소)가 가능한 최대 연속 펄스 부분 수열의 합
        answer = max - min;
        
        return answer;
    }
}