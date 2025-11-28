class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];

        // 모든 컴퓨터를 순회하며 네트워크 찾기
        for (int i = 0; i < n; i++) {
            // 아직 방문하지 않은 컴퓨터라면 새로운 네트워크의 시작점
            if (!visited[i]) {
                answer++; // 네트워크 개수 증가
                dfs(i, n, computers, visited); // 연결된 모든 컴퓨터 방문 처리
            }
        }
        
        return answer;
    }

    // DFS 함수: 현재 컴퓨터와 연결된 모든 컴퓨터를 찾아 방문 처리
    private void dfs(int node, int n, int[][] computers, boolean[] visited) {
        visited[node] = true; // 현재 컴퓨터 방문 처리

        for (int next = 0; next < n; next++) {
            // 1. 자기 자신이 아니고
            // 2. 연결되어 있으며 (값이 1)
            // 3. 아직 방문하지 않은 경우
            if (node != next && computers[node][next] == 1 && !visited[next]) {
                dfs(next, n, computers, visited); // 연결된 컴퓨터로 이동
            }
        }
    }
}