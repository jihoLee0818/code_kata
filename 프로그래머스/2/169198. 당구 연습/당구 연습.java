class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for (int i = 0; i < balls.length; i++) {
            int targetX = balls[i][0];
            int targetY = balls[i][1];

            // 최솟값을 저장할 변수 (초기값은 충분히 큰 수)
            int minDistanceSquared = Integer.MAX_VALUE;

            // 1. 위쪽 쿠션 (y = n 대칭)
            // 조건: x좌표가 같고, 목표 공이 시작점보다 위에 있으면 안됨 (벽보다 공을 먼저 맞춤)
            if (!(startX == targetX && targetY > startY)) {
                int dist = getDistanceSquared(startX, startY, targetX, 2 * n - targetY);
                minDistanceSquared = Math.min(minDistanceSquared, dist);
            }

            // 2. 아래쪽 쿠션 (y = 0 대칭)
            // 조건: x좌표가 같고, 목표 공이 시작점보다 아래에 있으면 안됨
            if (!(startX == targetX && targetY < startY)) {
                int dist = getDistanceSquared(startX, startY, targetX, -targetY);
                minDistanceSquared = Math.min(minDistanceSquared, dist);
            }

            // 3. 왼쪽 쿠션 (x = 0 대칭)
            // 조건: y좌표가 같고, 목표 공이 시작점보다 왼쪽에 있으면 안됨
            if (!(startY == targetY && targetX < startX)) {
                int dist = getDistanceSquared(startX, startY, -targetX, targetY);
                minDistanceSquared = Math.min(minDistanceSquared, dist);
            }

            // 4. 오른쪽 쿠션 (x = m 대칭)
            // 조건: y좌표가 같고, 목표 공이 시작점보다 오른쪽에 있으면 안됨
            if (!(startY == targetY && targetX > startX)) {
                int dist = getDistanceSquared(startX, startY, 2 * m - targetX, targetY);
                minDistanceSquared = Math.min(minDistanceSquared, dist);
            }

            answer[i] = minDistanceSquared;
        }

        return answer;
    }

    // 두 점 사이의 거리의 제곱을 반환하는 함수
    private int getDistanceSquared(int x1, int y1, int x2, int y2) {
        return (int) (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}