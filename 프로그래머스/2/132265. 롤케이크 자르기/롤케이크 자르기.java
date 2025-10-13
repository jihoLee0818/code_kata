import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        // 형의 토핑 종류와 개수를 저장할 Map
        Map<Integer, Integer> leftMap = new HashMap<>();
        // 동생의 토핑 종류와 개수를 저장할 Map
        Map<Integer, Integer> rightMap = new HashMap<>();

        // 1. 초기 상태: 동생이 모든 토핑을 가짐
        for (int t : topping) {
            rightMap.put(t, rightMap.getOrDefault(t, 0) + 1);
        }

        // 2. 자르는 지점을 이동하며 토핑을 하나씩 형에게 넘겨줌
        for (int t : topping) {
            // 형에게 토핑 추가
            leftMap.put(t, leftMap.getOrDefault(t, 0) + 1);

            // 동생에게서 토핑 제거
            rightMap.put(t, rightMap.get(t) - 1);
            // 만약 해당 토핑의 개수가 0이 되면 Map에서 완전히 제거
            if (rightMap.get(t) == 0) {
                rightMap.remove(t);
            }

            // 3. 형과 동생의 토핑 가짓수 비교
            // Map의 size()는 고유한 키의 개수를 반환하므로, 이것이 토핑의 가짓수가 됨
            if (leftMap.size() == rightMap.size()) {
                answer++;
            }
        }
        
        return answer;
    }
}