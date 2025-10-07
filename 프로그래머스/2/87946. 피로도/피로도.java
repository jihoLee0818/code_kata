class Solution {
    public int maxDungeons = 0; // 탐험 가능한 최대 던전 수
    public boolean[] visited;   // 던전 방문 여부

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(k, 0, dungeons);
        return maxDungeons;
    }

    /**
     * @param currentFatigue 현재 피로도
     * @param dungeonsExplored 현재까지 탐험한 던전 수
     * @param dungeons 던전 정보 배열
     */
    public void dfs(int currentFatigue, int dungeonsExplored, int[][] dungeons) {
        // 1. 현재까지 탐험한 던전 수로 최댓값 갱신
        maxDungeons = Math.max(maxDungeons, dungeonsExplored);

        // 2. 모든 던전을 순회
        for (int i = 0; i < dungeons.length; i++) {
            // 아직 방문하지 않았고, 현재 피로도로 입장이 가능한 경우
            if (!visited[i] && currentFatigue >= dungeons[i][0]) {
                // 3. 탐험 (방문 처리 및 재귀 호출)
                visited[i] = true;
                dfs(currentFatigue - dungeons[i][1], dungeonsExplored + 1, dungeons);
                
                // 4. 백트래킹 (다음 순서를 위해 방문 상태 원상 복구)
                visited[i] = false;
            }
        }
    }
}