import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        // 두 큐를 생성하고 초기 합을 long 타입으로 계산
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        long sum1 = 0;
        long sum2 = 0;
        
        for (int val : queue1) {
            sum1 += val;
            q1.offer(val);
        }
        for (int val : queue2) {
            sum2 += val;
            q2.offer(val);
        }

        long totalSum = sum1 + sum2;

        // 2. 불가능한 경우 (전체 합이 홀수)
        if (totalSum % 2 != 0) {
            return -1;
        }

        // 3. 목표 합
        long targetSum = totalSum / 2;

        int count = 0;
        // 4. 종료 조건 (두 큐 길이의 합 * 2 = 한 큐 길이 * 4)
        int limit = queue1.length * 4; 

        // 4. 시뮬레이션
        while (sum1 != targetSum) {
            // 실패 조건
            if (count > limit) {
                return -1;
            }

            if (sum1 > targetSum) {
                // q1에서 q2로 이동
                int val = q1.poll();
                sum1 -= val;
                sum2 += val;
                q2.offer(val);
            } else {
                // q2에서 q1로 이동
                int val = q2.poll();
                sum2 -= val;
                sum1 += val;
                q1.offer(val);
            }
            count++;
        }

        return count;
    }
}