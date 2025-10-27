import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    
    // 1. 그래프(인접 리스트) 생성
    ArrayList<Integer>[] graph;

    public int solution(int n, int[][] wires) {
        int minDifference = n; // 최댓값으로 초기화
        
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 인접 리스트 채우기
        for (int[] wire : wires) {
            graph[wire[0]].add(wire[1]);
            graph[wire[1]].add(wire[0]);
        }

        // 2. 모든 전선 순회
        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];

            // 3. 전선 임시 제거 (Object로 제거해야 인덱스가 아닌 값으로 제거됨)
            graph[v1].remove(Integer.valueOf(v2));
            graph[v2].remove(Integer.valueOf(v1));

            // 4. BFS로 v1쪽 송전탑 개수 세기
            int count = bfs(v1, n);
            
            // 5. 차이 계산 및 최솟값 갱신
            int diff = Math.abs(count - (n - count));
            minDifference = Math.min(minDifference, diff);

            // 6. 전선 복구
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
        
        return minDifference;
    }

    // BFS를 통해 start와 연결된 노드의 개수를 세는 함수
    private int bfs(int start, int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        
        queue.offer(start);
        visited[start] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            count++;

            for (int neighbor : graph[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        return count;
    }
}