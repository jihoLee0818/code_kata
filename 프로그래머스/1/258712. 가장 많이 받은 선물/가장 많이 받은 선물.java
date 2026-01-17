import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        
        // 1. 친구 이름을 인덱스로 매핑 (0 ~ n-1)
        Map<String, Integer> friendMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            friendMap.put(friends[i], i);
        }
        
        // 2. 데이터 저장 구조 초기화
        // giftGraph[i][j]: i가 j에게 준 선물 개수
        int[][] giftGraph = new int[n][n];
        // giftDegree[i]: i의 선물 지수 (준 선물 - 받은 선물)
        int[] giftDegree = new int[n];
        
        // 3. 선물 기록 처리
        for (String gift : gifts) {
            String[] parts = gift.split(" ");
            String giverName = parts[0];
            String receiverName = parts[1];
            
            int giver = friendMap.get(giverName);
            int receiver = friendMap.get(receiverName);
            
            // 주고받은 기록 갱신
            giftGraph[giver][receiver]++;
            
            // 선물 지수 갱신 (준 사람 +, 받은 사람 -)
            giftDegree[giver]++;
            giftDegree[receiver]--;
        }
        
        // 4. 다음 달 선물 예측
        int maxGifts = 0;
        
        // 모든 사람(i)에 대해 받을 선물의 수를 계산
        for (int i = 0; i < n; i++) {
            int currentMonthGifts = 0;
            
            for (int j = 0; j < n; j++) {
                if (i == j) continue; // 자기 자신과는 비교 제외
                
                // i가 j에게 선물을 받아야 하는지 판단
                
                // [규칙 1] 기록이 있고, 개수가 다른 경우: 더 많이 준 사람이 받음
                if (giftGraph[i][j] > giftGraph[j][i]) {
                    currentMonthGifts++;
                }
                // [규칙 2] 기록이 없거나(0 vs 0), 개수가 같은 경우: 선물 지수 비교
                else if (giftGraph[i][j] == giftGraph[j][i]) {
                    if (giftDegree[i] > giftDegree[j]) {
                        currentMonthGifts++;
                    }
                }
            }
            
            // 최댓값 갱신
            maxGifts = Math.max(maxGifts, currentMonthGifts);
        }
        
        return maxGifts;
    }
}