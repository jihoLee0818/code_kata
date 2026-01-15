import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        // 1. 이분 탐색 범위 설정
        long min = 1;
        long max = 100000; // diffs의 원소 최대값이 10만이므로 설정 (또는 루프돌며 max값 찾기)
        
        // diffs의 실제 최댓값을 찾아 범위를 좁히면 더 효율적입니다.
        for(int d : diffs) {
            if(d > max) max = d;
        }

        int answer = (int)max;

        // 2. 이분 탐색 수행
        while (min <= max) {
            long mid = (min + max) / 2;

            if (isPossible(mid, diffs, times, limit)) {
                // 제한 시간 내에 성공 -> 더 낮은 레벨도 가능한지 시도
                answer = (int)mid;
                max = mid - 1;
            } else {
                // 시간 초과 -> 레벨을 높여야 함
                min = mid + 1;
            }
        }

        return answer;
    }

    // 해당 level로 제한 시간 내에 풀 수 있는지 확인하는 함수
    private boolean isPossible(long level, int[] diffs, int[] times, long limit) {
        long totalTime = 0;

        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                // 숙련도가 충분한 경우
                totalTime += times[i];
            } else {
                // 숙련도가 부족하여 틀리는 경우
                int mistakes = (int)(diffs[i] - level);
                // 이전 문제 시간 + 현재 문제 시간
                long timePerMistake = times[i] + times[i - 1]; 
                
                // (틀린 횟수 * 소요 시간) + 이번 문제를 푸는 시간
                totalTime += (mistakes * timePerMistake) + times[i];
            }
            
            // 중간 계산에서 이미 limit를 넘으면 조기 종료 (최적화)
            if (totalTime > limit) return false;
        }

        return totalTime <= limit;
    }
}