import java.util.*;

class Solution {
    List<List<Integer>> graph = new ArrayList<>();
    
    // DP 배열은 재귀 함수 리턴값으로 대체하거나 전역으로 사용 가능
    // 여기서는 개념 이해를 위해 전역 변수 대신 재귀 리턴 방식을 사용합니다.
    // 하지만 N이 크므로 효율성을 위해 클래스 멤버 변수를 활용하는 것이 좋습니다.
    
    public int solution(int n, int[][] lighthouse) {
        // 1. 그래프 생성 (인접 리스트)
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : lighthouse) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        // 2. DFS 탐색 시작 (1번 노드를 임의의 루트로 설정)
        // 0: OFF 비용, 1: ON 비용
        int[] result = dfs(1, -1);
        
        // 루트 노드가 꺼진 경우와 켜진 경우 중 최솟값 반환
        return Math.min(result[0], result[1]);
    }
    
    /**
     * @param cur 현재 노드
     * @param parent 부모 노드 (역주행 방지)
     * @return int[] {내가 꺼졌을 때의 최소 비용, 내가 켜졌을 때의 최소 비용}
     */
    private int[] dfs(int cur, int parent) {
        int offCost = 0;
        int onCost = 1; // 나를 켜면 비용 1 발생
        
        for (int next : graph.get(cur)) {
            if (next == parent) continue;
            
            int[] childCost = dfs(next, cur);
            
            // 1. 내가 꺼짐 -> 자식은 무조건 켜져야 함
            offCost += childCost[1];
            
            // 2. 내가 켜짐 -> 자식은 켜지든 꺼지든 상관없음 (더 작은 값 선택)
            onCost += Math.min(childCost[0], childCost[1]);
        }
        
        return new int[]{offCost, onCost};
    }
}