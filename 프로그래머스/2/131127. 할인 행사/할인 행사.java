import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        // 1. 구매 희망 목록을 Map으로 변환
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }
        
        // 2. 첫 10일간의 할인 품목으로 초기 윈도우(Map) 생성
        Map<String, Integer> discountMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            discountMap.put(discount[i], discountMap.getOrDefault(discount[i], 0) + 1);
        }
        
        // 첫 10일에 대해 일치 여부 확인
        if (wantMap.equals(discountMap)) {
            answer++;
        }
        
        // 3. 슬라이딩 윈도우 시작 (11일째부터)
        for (int i = 10; i < discount.length; i++) {
            // 윈도우에서 빠지는 맨 앞 제품 (i-10번째)
            String removeProduct = discount[i - 10];
            discountMap.put(removeProduct, discountMap.get(removeProduct) - 1);
            if (discountMap.get(removeProduct) == 0) {
                discountMap.remove(removeProduct);
            }
            
            // 윈도우에 새로 들어오는 제품 (i번째)
            String addProduct = discount[i];
            discountMap.put(addProduct, discountMap.getOrDefault(addProduct, 0) + 1);
            
            // 맵 일치 여부 확인
            if (wantMap.equals(discountMap)) {
                answer++;
            }
        }
        
        return answer;
    }
}