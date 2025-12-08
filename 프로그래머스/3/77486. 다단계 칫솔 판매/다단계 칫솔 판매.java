import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        // 정답을 담을 배열
        int[] answer = new int[enroll.length];
        
        // 1. 조직도 관계와 인덱스를 저장할 Map 생성
        Map<String, String> parentMap = new HashMap<>(); // <자신, 추천인>
        Map<String, Integer> indexMap = new HashMap<>(); // <이름, 인덱스>
        
        for (int i = 0; i < enroll.length; i++) {
            parentMap.put(enroll[i], referral[i]);
            indexMap.put(enroll[i], i);
        }
        
        // 2. 판매 집계 데이터 하나씩 처리
        for (int i = 0; i < seller.length; i++) {
            String currentPerson = seller[i];
            int profit = amount[i] * 100; // 칫솔 개당 100원
            
            // 3. 추천인을 타고 올라가며 이익 분배 (Center "-"가 아닐 때까지)
            while (!currentPerson.equals("-") && profit > 0) {
                // 상사에게 줄 돈 (10% 절삭)
                int amountToDistribute = profit / 10;
                
                // 내가 가질 돈 (나머지)
                int amountToKeep = profit - amountToDistribute;
                
                // 내 계좌(answer)에 더하기
                // (indexMap을 통해 배열 인덱스를 바로 찾음)
                if (indexMap.containsKey(currentPerson)) {
                    int idx = indexMap.get(currentPerson);
                    answer[idx] += amountToKeep;
                }
                
                // 다음 루프를 위해 변수 갱신 (상사에게 올라감)
                currentPerson = parentMap.get(currentPerson);
                profit = amountToDistribute; // 이제 이 돈이 상사의 매출이 됨
            }
        }
        
        return answer;
    }
}