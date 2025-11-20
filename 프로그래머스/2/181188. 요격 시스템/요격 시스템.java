import java.util.Arrays;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;

        // 1. 끝나는 지점(e)을 기준으로 오름차순 정렬
        Arrays.sort(targets, (a, b) -> a[1] - b[1]);

        // 마지막으로 요격한 지점 (초기값은 -1)
        int lastBound = -1;

        for (int[] missile : targets) {
            int start = missile[0];
            int end = missile[1];

            // 2. 현재 미사일의 시작점이 마지막 요격 지점보다 크거나 같다면
            // 이전 요격 미사일로는 이 미사일을 맞출 수 없음 (개구간 (s, e) 조건 때문)
            if (start >= lastBound) {
                // 새로운 요격 미사일 발사
                answer++;
                // 요격 지점을 현재 미사일의 끝나는 지점(e)으로 갱신
                // (실제로는 e보다 아주 조금 작은 지점에서 요격하지만, 비교 편의상 e로 설정)
                lastBound = end;
            }
            // start < lastBound 인 경우는 이미 이전 요격에 포함되므로 패스
        }

        return answer;
    }
}