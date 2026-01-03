import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int[] wanho = scores[0];
        int wanhoSum = wanho[0] + wanho[1];
        
        // 1. 정렬
        // 근무 태도(a) 내림차순, 동점 시 동료 평가(b) 오름차순
        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });
        
        int maxPeerScore = -1; // 지금까지 확인한 동료 평가 점수의 최댓값
        int rank = 1; // 완호의 등수 (1등부터 시작)
        
        for (int[] s : scores) {
            // 2. 인센티브 수령 여부 판단 (Filtering)
            // 근무 태도는 이미 내림차순 정렬되어 있으므로, 동료 평가 점수만 비교하면 됨
            if (s[1] < maxPeerScore) {
                // 탈락 대상
                // 만약 탈락 대상이 완호라면 -1 반환
                if (s[0] == wanho[0] && s[1] == wanho[1]) {
                    return -1;
                }
            } else {
                // 인센티브 수령 가능 대상 (생존)
                maxPeerScore = Math.max(maxPeerScore, s[1]);
                
                // 3. 등수 계산
                // 완호보다 총점이 높은 사람 발견 시 등수 밀려남
                if (s[0] + s[1] > wanhoSum) {
                    rank++;
                }
            }
        }
        
        return rank;
    }
}