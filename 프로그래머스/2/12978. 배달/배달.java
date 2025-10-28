import java.util.*;

class Solution {
    
    // 그래프를 표현할 인접 리스트
    ArrayList<Node>[] graph;
    // 1번 마을로부터의 최단 거리를 저장할 배열
    int[] dist;

    // 마을 번호와 시간을 함께 저장할 Node 클래스
    class Node implements Comparable<Node> {
        int village;
        int time;

        Node(int village, int time) {
            this.village = village;
            this.time = time;
        }

        @Override
        public int compareTo(Node other) {
            // 시간이 짧은 순서대로 정렬
            return this.time - other.time;
        }
    }

    public int solution(int N, int[][] road, int K) {
        // 1. 그래프 및 거리 배열 초기화
        graph = new ArrayList[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE); // 모두 무한대로 초기화

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 1-1. 그래프 정보 입력 (양방향)
        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int time = r[2];
            graph[a].add(new Node(b, time));
            graph[b].add(new Node(a, time));
        }

        // 3. 우선순위 큐 및 시작점 설정
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[1] = 0; // 1번 마을은 0
        pq.offer(new Node(1, 0));

        // 4. 다익스트라 탐색
        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int currentVillage = currentNode.village;
            int currentTime = currentNode.time;

            // 이미 더 짧은 경로를 찾았다면 건너뜀
            if (currentTime > dist[currentVillage]) {
                continue;
            }

            // 현재 마을과 연결된 다음 마을들 탐색
            for (Node nextNode : graph[currentVillage]) {
                int newTime = currentTime + nextNode.time;
                
                // 새로운 경로가 기존 최단 경로보다 짧다면
                if (newTime < dist[nextNode.village]) {
                    dist[nextNode.village] = newTime;
                    pq.offer(new Node(nextNode.village, newTime));
                }
            }
        }

        // 5. 결과 집계
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }

        return answer;
    }
}