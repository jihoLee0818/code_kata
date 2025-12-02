import java.util.*;

class Solution {
    // 공항 연결 정보를 저장할 그래프 (Key: 출발지, Value: 도착지 우선순위 큐)
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    // 경로를 저장할 리스트 (LinkedList를 사용하여 맨 앞에 추가하는 연산을 효율적으로 함)
    LinkedList<String> route = new LinkedList<>();

    public String[] solution(String[][] tickets) {
        // 1. 그래프 생성
        for (String[] ticket : tickets) {
            String from = ticket[0];
            String to = ticket[1];
            
            // 해당 출발지가 없으면 PQ 생성 후 추가, 있으면 가져와서 추가
            graph.putIfAbsent(from, new PriorityQueue<>());
            graph.get(from).offer(to);
        }

        // 2. DFS 시작 (항상 ICN에서 시작)
        dfs("ICN");

        // 3. 리스트를 배열로 변환하여 반환
        return route.toArray(new String[0]);
    }

    // DFS 함수
    private void dfs(String airport) {
        // 현재 공항에서 갈 수 있는 다음 공항이 있는 동안 반복
        while (graph.containsKey(airport) && !graph.get(airport).isEmpty()) {
            // 알파벳 순서가 가장 빠른 도착지를 꺼냄 (티켓 사용)
            String nextAirport = graph.get(airport).poll();
            // 다음 공항으로 이동
            dfs(nextAirport);
        }
        
        // 더 이상 갈 곳이 없으면 경로의 '역순'으로 추가됨
        // 리스트의 맨 앞에 추가 (addFirst와 동일)
        route.add(0, airport);
    }
}