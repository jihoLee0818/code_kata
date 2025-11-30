import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        // 1. 그래프 생성 (인접 리스트)
        // 노드 번호가 1부터 시작하므로 n + 1 크기로 생성
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 양방향 간선 연결
        for (int[] e : edge) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        // 2. BFS 준비
        boolean[] visited = new boolean[n + 1];
        int[] distance = new int[n + 1];
        Queue<Integer> queue = new LinkedList<>();

        // 시작 노드(1번) 설정
        queue.offer(1);
        visited[1] = true;
        // distance[1] = 0 (생략 가능, 기본값 0)

        // 3. BFS 탐색
        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 현재 노드와 연결된 모든 노드 확인
            for (int next : graph[current]) {
                if (!visited[next]) {
                    visited[next] = true;
                    distance[next] = distance[current] + 1; // 거리 갱신
                    queue.offer(next);
                }
            }
        }

        // 4. 가장 먼 거리 찾기
        int maxDist = 0;
        for (int d : distance) {
            maxDist = Math.max(maxDist, d);
        }

        // 5. 가장 먼 거리에 있는 노드 개수 세기
        int answer = 0;
        for (int d : distance) {
            if (d == maxDist) {
                answer++;
            }
        }

        return answer;
    }
}