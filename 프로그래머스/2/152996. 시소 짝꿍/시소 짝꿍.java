import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        
        // 1. 배열을 정렬
        Arrays.sort(weights);
        
        // 2. 무게별 개수를 저장할 Map
        Map<Integer, Long> map = new HashMap<>();

        // 3. 정렬된 weights 배열을 순회
        for (int w : weights) {
            
            // --- 짝꿍 찾기 ---
            // 현재 w를 기준으로, 이전에 Map에 저장된 짝꿍들을 찾습니다.

            // 1) Case 1: 1:1 비율 (w == w)
            if (map.containsKey(w)) {
                answer += map.get(w);
            }

            // 2) Case 2: 2:3 비율 (w * 2 = partner * 3) -> partner = w * 2 / 3
            if (w % 3 == 0 && map.containsKey(w * 2 / 3)) {
                answer += map.get(w * 2 / 3);
            }

            // 3) Case 3: 1:2 비율 (w * 1 = partner * 2) -> partner = w / 2
            if (w % 2 == 0 && map.containsKey(w / 2)) {
                answer += map.get(w / 2);
            }

            // 4) Case 4: 3:4 비율 (w * 3 = partner * 4) -> partner = w * 3 / 4
            if (w % 4 == 0 && map.containsKey(w * 3 / 4)) {
                answer += map.get(w * 3 / 4);
            }
            
            // --- 현재 무게 Map에 등록 ---
            // 짝꿍을 모두 센 뒤, 현재 무게 w를 Map에 추가합니다.
            map.put(w, map.getOrDefault(w, 0L) + 1);
        }
        
        return answer;
    }
}