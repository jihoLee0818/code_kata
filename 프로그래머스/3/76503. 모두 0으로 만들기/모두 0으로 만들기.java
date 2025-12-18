import java.util.*;

class Solution {
    long answer = 0; // 연산 횟수 (범위가 클 수 있으므로 long)
    long[] weights;  // 가중치 배열 (연산 중 값이 커질 수 있으므로 long)
    ArrayList<Integer>[] graph; // 인접 리스트

    public long solution(int[] a, int[][] edges) {
        // 1. 불가능 조건 확인: 모든 가중치의 합이 0이어야 함
        long sum = 0;
        weights = new long[a.length];
        
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            weights[i] = a[i];
        }
        
        if (sum != 0) return -1;

        // 2. 트리(그래프) 생성
        graph = new ArrayList[a.length];
        for (int i = 0; i < a.length; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        // 3. DFS 탐색 (임의의 루트 0번부터 시작)
        // 부모 노드 정보가 없으므로 -1을 부모로 전달
        dfs(0, -1);

        return answer;
    }

    // 현재 노드(current)를 루트로 하는 서브트리를 처리하는 함수
    private void dfs(int current, int parent) {
        for (int next : graph[current]) {
            if (next != parent) { // 부모 방향으로 역류 방지
                dfs(next, current); // 자식 노드 먼저 처리 (재귀)
            }
        }

        // 자식 노드들을 다녀온 후(후위 순회), 현재 로직 수행
        // 루트 노드가 아니라면, 자신의 값을 부모에게 넘김
        if (parent != -1) {
            // 부모에게 내 가중치를 더해줌 (떠넘기기)
            weights[parent] += weights[current];
            
            // 이동한 만큼 연산 횟수 증가 (절댓값)
            answer += Math.abs(weights[current]);
        }
    }
}