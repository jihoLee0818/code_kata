import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        
        // 1. Min-Heap (오름차순 정렬)
        // 무적권을 사용한 라운드의 적의 수를 저장
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // 병사 수는 int 범위를 넘을 수 있으므로 long으로 관리
        long currentSoldierSum = 0; 
        long n_long = (long)n; 

        for (int i = 0; i < enemy.length; i++) {
            int currentWave = enemy[i];
            
            // 2. 일단 무적권 후보로 큐에 추가
            pq.add(currentWave);
            
            // 3. 무적권 개수(k)를 초과하면, 가장 적은 적의 라운드는 병사로 막음
            if (pq.size() > k) {
                // 큐에서 가장 작은 값을 꺼내(poll) 병사 합계에 더함
                currentSoldierSum += pq.poll();
            }
            
            // 4. 병사가 부족하면 게임 종료
            if (currentSoldierSum > n_long) {
                // i번째 라운드를 막지 못했으므로, i-1 라운드까지 성공.
                // i는 0부터 시작했으므로, 성공한 라운드 개수는 i.
                return i;
            }
        }
        
        // 5. 모든 라운드를 통과함
        return enemy.length;
    }
}