import java.util.Arrays;

class Solution {
    
    // 각 섬의 부모를 저장할 배열 (Union-Find 용)
    int[] parent;

    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        // 1. 건설 비용(costs[2])을 기준으로 오름차순 정렬
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);

        // 2. Union-Find 초기화: 처음엔 모든 섬의 부모가 자기 자신
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 3. 비용이 적은 다리부터 순회하며 연결
        for (int[] edge : costs) {
            int islandA = edge[0];
            int islandB = edge[1];
            int cost = edge[2];

            // 두 섬의 최상위 부모(루트)를 찾음
            int rootA = findParent(islandA);
            int rootB = findParent(islandB);

            // 두 섬의 부모가 다르면 (아직 연결되지 않았으면) -> 연결!
            if (rootA != rootB) {
                union(rootA, rootB);
                answer += cost;
            }
            // 부모가 같다면 이미 연결된 것이므로 패스 (사이클 방지)
        }

        return answer;
    }

    // [Find] 최상위 부모를 찾는 함수 (경로 압축 적용)
    private int findParent(int node) {
        if (parent[node] == node) {
            return node;
        }
        // 경로 압축: 찾으면서 부모를 루트로 바로 갱신
        return parent[node] = findParent(parent[node]);
    }

    // [Union] 두 집합을 합치는 함수
    private void union(int rootA, int rootB) {
        // 일반적으로 더 작은 값을 부모로 설정
        if (rootA < rootB) {
            parent[rootB] = rootA;
        } else {
            parent[rootA] = rootB;
        }
    }
}