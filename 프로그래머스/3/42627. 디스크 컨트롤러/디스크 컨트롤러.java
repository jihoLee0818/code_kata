import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int n = jobs.length;

        // 1. 원본 배열을 요청 시간(start) 오름차순으로 정렬
        // 요청 시간이 빠른 순서대로 큐에 넣기 위함
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        // 2. 처리할 작업들을 저장할 우선순위 큐 (소요 시간(duration) 오름차순 정렬)
        // [0]: 요청 시간, [1]: 소요 시간
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        int processedCount = 0; // 처리된 작업 개수
        int jobIdx = 0;         // jobs 배열 인덱스
        int currentTime = 0;    // 현재 시간 (하드디스크의 타임라인)

        // 모든 작업이 처리될 때까지 반복
        while (processedCount < n) {
            
            // 3. 현재 시간(currentTime)까지 들어온 모든 요청을 큐에 넣음
            while (jobIdx < n && jobs[jobIdx][0] <= currentTime) {
                pq.offer(jobs[jobIdx]);
                jobIdx++;
            }

            // 4. 큐에서 작업을 꺼내 수행
            if (!pq.isEmpty()) {
                int[] currentJob = pq.poll();
                
                // 작업 수행: 현재 시간에 소요 시간을 더함
                currentTime += currentJob[1];
                
                // 요청부터 종료까지 걸린 시간 누적 (현재 시간 - 요청 시간)
                answer += (currentTime - currentJob[0]);
                
                processedCount++;
            } 
            // 5. 큐가 비어있다면 (현재 시간까지 들어온 작업이 없음)
            else {
                // 다음 작업이 들어오는 시간으로 건너뜀
                currentTime = jobs[jobIdx][0];
            }
        }

        // 평균 시간 반환 (소수점 이하 버림)
        return answer / n;
    }
}