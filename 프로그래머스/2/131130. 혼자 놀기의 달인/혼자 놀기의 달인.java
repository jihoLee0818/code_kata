import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int solution(int[] cards) {
        int n = cards.length;
        boolean[] visited = new boolean[n];
        List<Integer> groupSizes = new ArrayList<>();

        // 2. 1번 상자부터 n번 상자까지 순회
        for (int i = 0; i < n; i++) {
            // 3. 아직 방문하지 않은 상자라면 새 그룹 탐색 시작
            if (!visited[i]) {
                int currentBox = i;
                int currentSize = 0;

                // 4. DFS(Cycle) 탐색
                while (!visited[currentBox]) {
                    visited[currentBox] = true;
                    currentSize++;
                    // 카드는 1-based, 인덱스는 0-based이므로 -1
                    currentBox = cards[currentBox] - 1; 
                }
                
                // 탐색이 끝나면 그룹 크기 저장
                groupSizes.add(currentSize);
            }
        }

        // 5. 점수 계산
        // 5-1. 그룹이 1개뿐이면 0점
        if (groupSizes.size() < 2) {
            return 0;
        }

        // 5-2. 내림차순 정렬
        Collections.sort(groupSizes, Collections.reverseOrder());

        // 5-3. 가장 큰 두 그룹의 크기를 곱함
        return groupSizes.get(0) * groupSizes.get(1);
    }
}