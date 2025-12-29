import java.util.*;

class Solution {

    static int k;
    static int n;
    static int[][] reqs;
    static int[][] wait;

    public int solution(int k, int n, int[][] reqs) {
        this.k = k;
        this.n = n;
        this.reqs = reqs;

        // 각 상담 유형 별, 멘토 수에 따라 기다린 시간 계산
        wait = new int[k + 1][n - k + 2];
        for (int i = 1; i <= k; i++) {
            getWaitingTime(i);
        }

        // 최적의 멘토 배치로 최소 대기 시간 구하기
        return getMinTime();
    }

    // 상담 유형 index에 대해, 멘토 수 i일 때의 총 대기 시간 계산
    public void getWaitingTime(int index) {
        for (int i = 1; i <= n - k + 1; i++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            int time = 0;

            for (int j = 0; j < reqs.length; j++) {
                if (reqs[j][2] == index) {

                    // 멘토 수가 아직 다 안 차면 상담 바로 배정
                    if (pq.size() < i) {
                        pq.add(reqs[j][0] + reqs[j][1]);
                    } else {
                        int earliest = pq.poll();
                        if (earliest > reqs[j][0]) {
                            // 기다리면서 상담 시작
                            time += (earliest - reqs[j][0]);
                            pq.add(earliest + reqs[j][1]);
                        } else {
                            pq.add(reqs[j][0] + reqs[j][1]);
                        }
                    }
                }
            }
            // index 유형에서 i명의 멘토가 있을 때 대기 시간
            wait[index][i] = time;
        }
    }

    // n명의 멘토를 k개의 유형에 배치하여 최소 대기 시간 구하기
    public int getMinTime() {

        int[] num = new int[k + 1];
        Arrays.fill(num, 1); // 각 유형에 최소 1명 배정

        // 남은 멘토 수만큼 더 배치
        for (int i = 0; i < n - k; i++) {
            int maxDiff = 0;
            int idx = 1;

            // 각 유형에 멘토 한 명 늘렸을 때 대기시간 감소량 계산
            for (int j = 1; j <= k; j++) {
                int diff = wait[j][num[j]] - wait[j][num[j] + 1];
                if (diff > maxDiff) {
                    maxDiff = diff;
                    idx = j;
                }
            }
            num[idx]++; // 감소량이 가장 큰 유형에 멘토 한 명 추가
        }

        int answer = 0;
        // 최종 배치된 멘토 수에 따른 총 대기 시간 합
        for (int j = 1; j <= k; j++) {
            answer += wait[j][num[j]];
        }
        return answer;
    }
}
