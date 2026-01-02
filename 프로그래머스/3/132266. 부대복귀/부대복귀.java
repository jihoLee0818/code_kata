import java.util.*;

class Solution {
    List<List<Integer>> graph;
    int[] dist;

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // 1. 그래프 초기화 (인접 리스트)
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 양방향 도로 연결
        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }

        // 2. 거리 배열 초기화 (-1로 채움)
        dist = new int[n + 1];
        Arrays.fill(dist, -1);

        // 3. 목적지(destination)에서부터 BFS 시작
        bfs(destination);

        // 4. 결과 매핑
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]];
        }

        return answer;
    }

    private void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        dist[start] = 0; // 시작점 거리는 0

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : graph.get(current)) {
                // 아직 방문하지 않은 곳(거리가 -1)이라면 거리 갱신
                if (dist[next] == -1) {
                    dist[next] = dist[current] + 1;
                    q.add(next);
                }
            }
        }
    }
}