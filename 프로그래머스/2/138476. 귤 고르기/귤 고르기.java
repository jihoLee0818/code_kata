import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        // 1. 귤 크기별로 개수를 세기 위한 HashMap 생성
        Map<Integer, Integer> map = new HashMap<>();
        for (int size : tangerine) {
            map.put(size, map.getOrDefault(size, 0) + 1);
        }

        // 2. 개수(value)만 추출하여 리스트에 담기
        List<Integer> counts = new ArrayList<>(map.values());
        
        // 3. 개수를 기준으로 내림차순 정렬
        counts.sort(Collections.reverseOrder());

        int answer = 0; // 선택한 귤 종류의 수
        int tangerineCount = 0; // 상자에 담은 귤의 총 개수
        
        // 4. 개수가 많은 종류부터 상자에 담기
        for (int count : counts) {
            // 이번 종류의 귤을 상자에 담는다.
            tangerineCount += count;
            answer++;
            
            // 상자에 담은 귤의 개수가 k개 이상이 되면,
            // 현재까지 선택한 종류의 수가 최소값이므로 반복을 중단한다.
            if (tangerineCount >= k) {
                break;
            }
        }
        
        return answer;
    }
}