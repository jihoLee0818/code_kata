import java.util.*;

class Solution {
    // 그래프 탐색을 위한 노드 클래스
    static class Node implements Comparable<Node> {
        int id;
        int weight;

        public Node(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }

        // PriorityQueue에서 intensity가 작은 순서대로 정렬
        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 1. 그래프 생성 (인접 리스트)
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            int u = path[0];
            int v = path[1];
            int w = path[2];
            
            // 양방향 등산로
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }

        // 2. 산봉우리인지 빠르게 확인하기 위한 Set 또는 배열
        // (산봉우리에 도착하면 더 이상 나아가면 안 됨)
        Set<Integer> summitSet = new HashSet<>();
        for (int s : summits) {
            summitSet.add(s);
        }

        // 3. 다익스트라 알고리즘 준비
        // intensities[i]: i번 지점까지 갈 때 겪는 최소 intensity
        int[] intensities = new int[n + 1];
        Arrays.fill(intensities, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 4. 모든 출입구를 시작점으로 설정 (Multi-Source BFS/Dijkstra)
        for (int gate : gates) {
            pq.offer(new Node(gate, 0));
            intensities[gate] = 0;
        }

        // 5. 다익스트라 수행
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currNode = current.id;
            int currWeight = current.weight;

            // 현재 저장된 intensity보다 크면 굳이 탐색할 필요 없음 (가지치기)
            if (currWeight > intensities[currNode]) {
                continue;
            }

            // 현재 위치가 산봉우리라면, 더 이상 이동하지 않음 (목적지 도착)
            if (summitSet.contains(currNode)) {
                continue;
            }

            // 인접 노드 탐색
            for (Node next : graph.get(currNode)) {
                int nextNode = next.id;
                int weight = next.weight;

                // 새로운 경로의 intensity는 (지금까지의 최대, 현재 간선) 중 큰 값
                int newIntensity = Math.max(currWeight, weight);

                // 더 작은 intensity로 도달 가능하다면 갱신
                if (newIntensity < intensities[nextNode]) {
                    intensities[nextNode] = newIntensity;
                    pq.offer(new Node(nextNode, newIntensity));
                }
            }
        }

        // 6. 결과 찾기 (산봉우리 중 intensity가 최소인 것, 번호가 작은 것)
        int minIntensity = Integer.MAX_VALUE;
        int bestSummit = -1;

        // 산봉우리 번호 순서대로 정렬해야 "번호가 낮은 산봉우리" 조건을 쉽게 처리 가능
        Arrays.sort(summits);

        for (int summit : summits) {
            if (intensities[summit] < minIntensity) {
                minIntensity = intensities[summit];
                bestSummit = summit;
            }
        }

        return new int[]{bestSummit, minIntensity};
    }
}