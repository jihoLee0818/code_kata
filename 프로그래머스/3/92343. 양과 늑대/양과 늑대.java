import java.util.*;

class Solution {
    int[] info;
    List<List<Integer>> graph;
    int maxSheep = 0;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        
        // 1. 그래프 생성
        graph = new ArrayList<>();
        for (int i = 0; i < info.length; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        // 2. DFS 시작
        // 초기 후보: 루트 노드(0) 하나만 존재
        List<Integer> startList = new ArrayList<>();
        startList.add(0);
        
        dfs(0, 0, 0, startList);
        
        return maxSheep;
    }

    /**
     * @param idx   현재 방문한 노드 인덱스
     * @param sheep 현재까지 누적된 양의 수 (현재 노드 포함 전)
     * @param wolf  현재까지 누적된 늑대의 수 (현재 노드 포함 전)
     * @param candidates 현재 시점에서 방문 가능한 노드들의 리스트
     */
    private void dfs(int idx, int sheep, int wolf, List<Integer> candidates) {
        // 1. 현재 노드의 양/늑대 정보 반영
        if (info[idx] == 0) sheep++;
        else wolf++;

        // 2. 제약 조건 확인: 늑대가 양보다 많거나 같으면 종료
        if (wolf >= sheep) return;

        // 3. 최댓값 갱신
        maxSheep = Math.max(maxSheep, sheep);

        // 4. 다음 후보 리스트 생성
        // 기존 후보들 + 내 자식들 - 나 자신
        List<Integer> nextCandidates = new ArrayList<>(candidates);
        nextCandidates.remove(Integer.valueOf(idx)); // 현재 방문한 노드는 후보에서 제거
        nextCandidates.addAll(graph.get(idx));       // 내 자식들을 후보에 추가

        // 5. 후보들에 대해 재귀 호출 (완전 탐색)
        for (int next : nextCandidates) {
            dfs(next, sheep, wolf, nextCandidates);
        }
    }
}