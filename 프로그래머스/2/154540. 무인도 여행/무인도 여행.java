import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    
    // 맵의 크기 및 방문 여부, 맵 자체를 멤버 변수로
    int rows, cols;
    boolean[][] visited;
    String[] maps;
    
    // 상하좌우 이동을 위한 배열
    int[] dr = {-1, 1, 0, 0}; // 행 (상, 하)
    int[] dc = {0, 0, -1, 1}; // 열 (좌, 우)

    public int[] solution(String[] maps) {
        this.maps = maps;
        rows = maps.length;
        cols = maps[0].length();
        visited = new boolean[rows][cols];
        
        List<Integer> islandSums = new ArrayList<>();

        // 1. 맵 순회
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char cell = maps[i].charAt(j);
                
                // 3. 새로운 섬 발견
                if (cell != 'X' && !visited[i][j]) {
                    int sum = dfs(i, j); // 4. DFS 탐색 시작
                    islandSums.add(sum); // 5. 결과 저장
                }
            }
        }

        // 6. 정렬 및 반환
        if (islandSums.isEmpty()) {
            return new int[]{-1};
        }

        Collections.sort(islandSums); // 오름차순 정렬
        
        return islandSums.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * DFS 탐색 함수
     * @param r 현재 행
     * @param c 현재 열
     * @return 현재 연결된 섬의 총 식량 합
     */
    private int dfs(int r, int c) {
        // [종료 조건 1] 맵 범위를 벗어나는 경우
        if (r < 0 || r >= rows || c < 0 || c >= cols) {
            return 0;
        }

        // [종료 조건 2] 바다('X')이거나 이미 방문한 경우
        if (maps[r].charAt(c) == 'X' || visited[r][c]) {
            return 0;
        }

        // [현재 노드 처리]
        visited[r][c] = true; // 방문 처리
        int currentFood = maps[r].charAt(c) - '0'; // char to int 변환
        
        int totalFood = currentFood;

        // [다음 노드 탐색] 상하좌우 4방향 재귀 호출
        for (int i = 0; i < 4; i++) {
            totalFood += dfs(r + dr[i], c + dc[i]);
        }
        
        return totalFood;
    }
}