import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        // 1. 이분 탐색을 위해 times 정렬 (최대 시간을 구하기 위함)
        Arrays.sort(times);
        
        // 탐색 범위 설정 (long 타입 주의)
        long left = 1;
        // 최악의 경우: 가장 느린 심사관이 모든 사람을 처리할 때
        long right = (long) times[times.length - 1] * n;
        
        long answer = right; // 일단 최악의 경우를 정답으로 초기화

        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0; // mid 시간 동안 처리할 수 있는 총 인원 수

            // 각 심사관별로 mid 시간 동안 처리 가능한 인원 계산
            for (int time : times) {
                sum += mid / time;
            }

            // n명 이상 처리가 가능하다면 (시간이 충분함)
            if (sum >= n) {
                answer = mid;      // 현재 시간을 정답 후보로 저장
                right = mid - 1;   // 더 짧은 시간도 가능한지 탐색 (왼쪽으로 이동)
            } 
            // n명 처리가 불가능하다면 (시간이 부족함)
            else {
                left = mid + 1;    // 시간을 늘려야 함 (오른쪽으로 이동)
            }
        }
        
        return answer;
    }
}