import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

class Solution {
    public int solution(int x, int y, int n) {
        // x에서 y까지의 거리를 저장할 배열 (y가 최대 1,000,000이므로 크기 +1)
        int[] dp = new int[y + 1];
        // 모든 거리를 -1 (방문하지 않음)로 초기화
        Arrays.fill(dp, -1);

        Queue<Integer> queue = new LinkedList<>();

        // 시작점 x를 큐에 넣고, 거리는 0으로 설정
        queue.offer(x);
        dp[x] = 0;

        while (!queue.isEmpty()) {
            int currentNum = queue.poll();

            // 목표 y에 도달했다면 최소 거리가 갱신될 필요가 없으므로 건너뛴다.
            // 이미 dp[y]에 최소값이 있다면 굳이 다시 계산하지 않아도 된다.
            // 하지만 현재 코드는 dp[y] 값이 -1이 아닌 경우 (이미 방문) 다음 탐색으로 넘어간다.
            // 여기서 currentNum이 y와 같으면 바로 dp[y]를 리턴해도 된다.
            if (currentNum == y) {
                return dp[y];
            }

            // 3가지 연산 적용: +n, *2, *3
            int[] nextNums = {currentNum + n, currentNum * 2, currentNum * 3};

            for (int nextNum : nextNums) {
                // 다음 숫자가 y보다 작거나 같고, 아직 방문하지 않았다면 (-1)
                if (nextNum <= y && dp[nextNum] == -1) {
                    dp[nextNum] = dp[currentNum] + 1; // 연산 횟수 갱신
                    queue.offer(nextNum);             // 큐에 추가하여 다음 탐색 준비
                }
            }
        }

        // 큐가 모두 비워질 때까지 y에 도달하지 못했다면 -1 반환
        return dp[y];
    }
}